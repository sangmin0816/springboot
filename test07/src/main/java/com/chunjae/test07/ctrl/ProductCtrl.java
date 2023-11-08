package com.chunjae.test07.ctrl;

import com.chunjae.test07.biz.ProductService;
import com.chunjae.test07.domain.ProductVO;
import com.chunjae.test07.entity.Board;
import com.chunjae.test07.entity.FileData;
import com.chunjae.test07.entity.Product;
import com.chunjae.test07.entity.Used;
import com.chunjae.test07.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/product/**")
public class ProductCtrl {
    @Autowired
    private ProductService productService;

    @GetMapping("productList")
    public String productList(HttpServletRequest request, Model model){
        String tableName = request.getParameter("table");
        Page page = Page.pageStart(request, model);
        List<ProductVO> productList = productService.productList(page);
        int total = productList.size();
        Page.pageEnd(request, model, page, total);

        model.addAttribute("productList", productList);
        return "product/productList";
    }

    @GetMapping("productDetail")
    public String productDetail(int productNo, Model model){
        ProductVO vo = productService.productDetail(productNo);

        model.addAttribute("product", vo.getProduct());
        model.addAttribute("used", vo.getUsed());
        model.addAttribute("thumbnail", vo.getThumbnail());
        model.addAttribute("images", vo.getFiles());
        return "product/productDetail";
    }

    @GetMapping("productWrite")
    public String productWrite(){
        return "product/productWrite";
    }

    @PostMapping("productWrite")
    public String productWrite(MultipartHttpServletRequest files, HttpServletRequest req){
        String realFolder = req.getRealPath("/resources/upload");

        Enumeration<String> e = files.getParameterNames();
        Map map = new HashMap();
        while (e.hasMoreElements()) {
            String name = e.nextElement();
            String value = files.getParameter(name);
            map.put(name, value);
        }

        Product p = new Product();
        p.setTableName("used");
        p.setCategoryNo(Integer.parseInt((String)map.get("categoryNo")));
        p.setTitle((String) map.get("title"));
        p.setContent((String) map.get("content"));
        p.setPrice(Integer.parseInt((String)map.get("price")));
        p.setFree(Boolean.parseBoolean((String)map.get("isFree")));

        Used u = new Used();
        u.setUserId("author");
        u.setDiscount(Boolean.parseBoolean((String)map.get("isDiscount")));
        u.setAddr1((String) map.get("addr1"));
        u.setAddr2((String) map.get("addr2"));

        String today = new SimpleDateFormat("yyMMdd").format(new Date());
        String saveFolder = realFolder + File.separator + today;
        File folder = new File(saveFolder);

        if(!folder.exists()){
            folder.mkdirs();
        }

        List<MultipartFile> fileList = files.getFiles("uploadFiles");
        List<FileData> fileDataList = new ArrayList<>();
        FileData thumbnail = new FileData();

        int i = 0;
        for(MultipartFile multipartFile : fileList){
            String originalName = multipartFile.getOriginalFilename();
            if(!originalName.isEmpty()){
                String saveName = UUID.randomUUID().toString()+"_"+originalName;

                FileData f = new FileData();
                f.setOriginName(originalName);
                f.setSaveName(saveName);
                f.setFileType(multipartFile.getContentType());
                f.setSavePath(today);
                if(i==0){
                    thumbnail = f;
                } else{
                    fileDataList.add(f);
                }

                File savefile = new File(saveFolder, saveName);
                i++;

                try{
                    multipartFile.transferTo(savefile);
                } catch (Exception except){
                    System.out.println(except.getMessage());
                }
            }
        }

        ProductVO pv = new ProductVO();
        pv.setProduct(p);
        pv.setUsed(u);
        pv.setThumbnail(thumbnail);
        pv.setFiles(fileDataList);

        productService.productWrite(pv);

        return "redirect:productList";
    }
}
