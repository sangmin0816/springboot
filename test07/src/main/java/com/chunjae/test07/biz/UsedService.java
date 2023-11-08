package com.chunjae.test07.biz;

import com.chunjae.test07.domain.UsedVO;
import com.chunjae.test07.util.Page;

import java.util.List;

public interface UsedService {
    public List<UsedVO> usedList(Page page);
    public UsedVO usedDetail(int usedNo);
    public int usedWrite(UsedVO vo);
    public int usedEdit(UsedVO vo);

    public int usedStatusUpdate(int usedNo, String status);
    public int usedBaseUpdate(int usedNo);
    public int usedRemoveUpdate(int usedNo);
}
