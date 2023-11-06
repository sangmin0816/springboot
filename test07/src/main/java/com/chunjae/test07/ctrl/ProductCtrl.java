package com.chunjae.test07.ctrl;

import com.chunjae.test07.biz.ProductService;
import com.chunjae.test07.domain.ProductVO;
import com.chunjae.test07.entity.Board;
import com.chunjae.test07.entity.Product;
import com.chunjae.test07.entity.Used;
import com.chunjae.test07.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/product/**")
public class ProductCtrl {
    @Autowired
    private ProductService productService;

    @GetMapping("productList")
    public String productList(HttpServletRequest request, Model model){
        String tableName = request.getParameter("table");
        Page page = Page.pageStart(request, model);
        page.setTableName(tableName);
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
    public String productWrite(HttpServletRequest request){
        int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int price = Integer.parseInt(request.getParameter("price"));
        boolean isFree = Boolean.parseBoolean(request.getParameter("isFree"));

        Product p = new Product();
        p.setTableName("used");
        p.setCategoryNo(categoryNo);
        p.setTitle(title);
        p.setContent(content);
        p.setPrice(price);
        p.setFree(isFree);

        Used u = new Used();

        ProductVO pv = new ProductVO();
        pv.setProduct(p);
        pv.setUsed(u);

        return "redirect:productList";
    }
}
