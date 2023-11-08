package com.chunjae.test07.biz;

import com.chunjae.test07.domain.ProductVO;
import com.chunjae.test07.util.Page;

import java.util.List;

public interface ProductService {
    public List<ProductVO> productList(Page page);
    public ProductVO productDetail(int productNo);
    public int productWrite(ProductVO vo);
    public int productEdit(ProductVO vo);

    public int productStatusUpdate(int productNo, String status);
    public int productBaseUpdate(int productNo);
    public int productRemoveUpdate(int productNo);
}
