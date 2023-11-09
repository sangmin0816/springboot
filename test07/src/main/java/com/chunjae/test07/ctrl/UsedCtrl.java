package com.chunjae.test07.ctrl;

import com.chunjae.test07.biz.UsedService;
import com.chunjae.test07.domain.UsedVO;
import com.chunjae.test07.entity.FileData;
import com.chunjae.test07.entity.Used;
import com.chunjae.test07.entity.Used;
import com.chunjae.test07.entity.UsedProduct;
import com.chunjae.test07.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/used/**")
public class UsedCtrl {
    @Autowired
    private UsedService usedService;

    @GetMapping("usedList")
    public String usedList(HttpServletRequest request, Model model){
        Page page = Page.pageStart(request, model);
        List<UsedVO> usedList = usedService.usedList(page);
        int total = usedList.size();
        Page.pageEnd(request, model, page, total);

        model.addAttribute("usedList", usedList);
        return "used/usedList";
    }

    @PostMapping("usedList")
    public String usedListPost(HttpServletRequest request, Model model){
        Page page = Page.pageStart(request, model);
        List<UsedVO> usedList = usedService.usedList(page);
        int total = usedList.size();
        Page.pageEnd(request, model, page, total);

        model.addAttribute("usedList", usedList);
        return "used/usedList";
    }

    @GetMapping("usedDetail")
    public String usedDetail(int usedNo, Model model){
        UsedVO vo = usedService.usedDetail(usedNo);

        model.addAttribute("used", vo.getUsedProduct());
        model.addAttribute("thumbnail", vo.getThumbnail());
        model.addAttribute("images", vo.getFiles());
        return "used/usedDetail";
    }

    @GetMapping("usedWrite")
    public String usedWrite(){
        return "used/usedWrite";
    }

    @PostMapping("usedWrite")
    public String usedWrite(MultipartHttpServletRequest files, HttpServletRequest req){
        String realFolder = req.getServletContext().getRealPath("/resources/upload");

        Enumeration<String> e = files.getParameterNames();
        Map map = new HashMap();
        while (e.hasMoreElements()) {
            String name = e.nextElement();
            String value = files.getParameter(name);
            map.put(name, value);
        }

        UsedProduct u = new UsedProduct();
        u.setCategoryNo(Integer.parseInt((String)map.get("categoryNo")));
        u.setUserId("author");
        u.setTitle((String) map.get("title"));
        u.setContent((String) map.get("content"));
        u.setPrice(Integer.parseInt((String)map.get("price")));
        u.setFree(Boolean.parseBoolean((String)map.get("free")));
        u.setTpay(Boolean.parseBoolean((String)map.get("discount")));
        u.setDiscount(Boolean.parseBoolean((String)map.get("tpay")));
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

        for(MultipartFile multipartFile : fileList){
            String originalName = multipartFile.getOriginalFilename();
            if(!originalName.isEmpty()){
                String saveName = UUID.randomUUID().toString()+"_"+originalName;

                FileData f = new FileData();
                f.setOriginName(originalName);
                f.setSaveName(saveName);
                f.setFileType(multipartFile.getContentType());
                f.setSavePath(today);

                fileDataList.add(f);

                File savefile = new File(saveFolder, saveName);

                try{
                    multipartFile.transferTo(savefile);
                } catch (Exception except){
                    System.out.println(except.getMessage());
                }
            }
        }

        UsedVO uv = new UsedVO();
        uv.setUsedProduct(u);
        uv.setFiles(fileDataList);

        usedService.usedWrite(uv);

        return "redirect:usedList";
    }
}
