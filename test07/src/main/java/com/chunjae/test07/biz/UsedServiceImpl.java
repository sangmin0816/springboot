package com.chunjae.test07.biz;

import com.chunjae.test07.domain.UsedVO;
import com.chunjae.test07.entity.FileData;
import com.chunjae.test07.entity.UsedProduct;
import com.chunjae.test07.mapper.FileDataMapper;
import com.chunjae.test07.mapper.UsedProductMapper;
import com.chunjae.test07.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsedServiceImpl implements UsedService{
    @Autowired
    private UsedProductMapper mapper;
    @Autowired
    private FileDataMapper fileMapper;

    @Override
    public List<UsedVO> usedList(Page page) {
        List<UsedVO> usedVO = new ArrayList<>();
        List<UsedProduct> ulist = mapper.usedProductList(page);
        for(UsedProduct u: ulist){
            UsedVO temp = new UsedVO();
            temp.setUsedProduct(u);
            List<FileData> flist = fileMapper.fileDataBoardList("usedProduct", u.getUsedNo());
            if(flist.size()>0){
                temp.setThumbnail(flist.get(0));

            }

            usedVO.add(temp);
        }

        return usedVO;
    }

    @Override
    public UsedVO usedDetail(int usedNo) {
        UsedVO temp = new UsedVO();
        temp.setUsedProduct(mapper.usedProductGet(usedNo));
        List<FileData> flist = fileMapper.fileDataBoardList("usedProduct", usedNo);
        temp.setThumbnail(flist.get(0));
        temp.setFiles(flist);
        return temp;
    }

    @Transactional
    @Override
    public int usedWrite(UsedVO vo) {
        UsedProduct up = vo.getUsedProduct();
        int returnNo = mapper.usedProductInsert(up);
        int usedNo = mapper.usedProductGetLast();

        for(FileData f: vo.getFiles()){
            f.setTableName("usedProduct");
            f.setBoardNo(usedNo);
            fileMapper.fileDataInsert(f);
        }

        return returnNo;
    }

    @Override
    public int usedEdit(UsedVO vo) {
        UsedProduct up = vo.getUsedProduct();
        int returnNo = mapper.usedProductUpdate(up);
        int usedNo = up.getUsedNo();

        for(FileData f: vo.getFiles()){
            f.setTableName("usedProduct");
            f.setBoardNo(usedNo);
            fileMapper.fileDataInsert(f);
        }

        return returnNo;
    }

    @Override
    public int usedStatusUpdate(int usedNo, String status) {
        return mapper.usedProductStatusUpdate(usedNo, status);
    }

    @Override
    public int usedBaseUpdate(int usedNo) {
        return mapper.usedProductBaseUpdate(usedNo);
    }

    @Override
    public int usedRemoveUpdate(int usedNo) {
        return mapper.usedProductRemoveUpdate(usedNo);
    }
}
