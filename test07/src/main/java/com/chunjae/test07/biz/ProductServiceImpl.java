package com.chunjae.test07.biz;

import com.chunjae.test07.domain.ProductVO;
import com.chunjae.test07.entity.FileData;
import com.chunjae.test07.entity.Product;
import com.chunjae.test07.entity.Used;
import com.chunjae.test07.mapper.FileDataMapper;
import com.chunjae.test07.mapper.ProductMapper;
import com.chunjae.test07.mapper.UsedMapper;
import com.chunjae.test07.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private UsedMapper usedMapper;
    @Autowired
    private FileDataMapper fileMapper;

    @Override
    public List<ProductVO> productList(Page page) {
        List<ProductVO> pv = new ArrayList<>();
        List<Product> plist = productMapper.productList(page);
        for(Product p: plist){
            ProductVO temp = new ProductVO();
            temp.setProduct(p);

            FileData f = fileMapper.fileDataGet(p.getImageFile());
            temp.setThumbnail(f);

            pv.add(temp);
        }

        return pv;
    }

    @Override
    public ProductVO productDetail(int productNo) {
        ProductVO pv = new ProductVO();
        Product p = productMapper.productGet(productNo);
        pv.setProduct(p);

        if(p.getTableName().equals("used")){
            pv.setUsed(usedMapper.usedGet(productNo));
        }
        List<FileData> files = fileMapper.fileDataBoardList("product", productNo);
        pv.setFiles(files);

        FileData f = fileMapper.fileDataGet(p.getImageFile());
        pv.setThumbnail(f);

        return pv;
    }

    @Transactional
    @Override
    public int productWrite(ProductVO vo) {
        Product p = vo.getProduct();
        fileMapper.fileDataInsert(vo.getThumbnail());
        int fileNo = fileMapper.fileDataGetLast();
        p.setImageFile(fileNo);

        int returnNo = productMapper.productInsert(p);
        int productNo = productMapper.productGetLast();

        FileData thumbnail = fileMapper.fileDataGet(fileNo);
        thumbnail.setBoardNo(productNo); thumbnail.setTableName("product");
        fileMapper.fileDataUpdate(thumbnail);

        for(FileData f: vo.getFiles()){
            f.setTableName("product");
            f.setBoardNo(productNo);
            fileMapper.fileDataInsert(f);
        }

        String tableName = p.getTableName();
        if(tableName.equals("used")){
            Used u = vo.getUsed();
            u.setProductNo(productNo);
            usedMapper.usedInsert(u);
        }
        return returnNo;
    }

    @Transactional
    @Override
    public int productEdit(ProductVO vo) {
        Product p = vo.getProduct();
        if(vo.getThumbnail()!=null){
            FileData thumbnail = vo.getThumbnail();
            thumbnail.setTableName("product");
            thumbnail.setBoardNo(p.getProductNo());

            fileMapper.fileDataInsert(vo.getThumbnail());
            int fileNo = fileMapper.fileDataGetLast();
            p.setImageFile(fileNo);
        }

        int returnNo = productMapper.productInsert(p);
        int productNo = productMapper.productGetLast();

        for(FileData f: vo.getFiles()){
            f.setTableName("product");
            f.setBoardNo(productNo);
            fileMapper.fileDataInsert(f);
        }

        String tableName = p.getTableName();
        if(tableName.equals("used")){
            usedMapper.usedUpdate(vo.getUsed());
        }
        return returnNo;
    }

    @Override
    public int productStatusUpdate(int productNo, String status) {
        return productMapper.productStatusUpdate(productNo, status);
    }

    @Override
    public int productBaseUpdate(int productNo) {
        return productMapper.productBaseUpdate(productNo);
    }

    @Override
    public int productRemoveUpdate(int productNo) {
        int returnNo = productMapper.productRemoveUpdate(productNo);
        List<FileData> flist = fileMapper.fileDataBoardList("product", productNo);
        for(FileData f: flist){
            fileMapper.fileDataDelete(f.getFileNo());
        }

        return returnNo;
    }
}
