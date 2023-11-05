package com.chunjae.test07.biz;

import com.chunjae.test07.domain.BoardVO;
import com.chunjae.test07.entity.Board;
import com.chunjae.test07.entity.FileData;
import com.chunjae.test07.entity.Response;
import com.chunjae.test07.mapper.BoardMapper;
import com.chunjae.test07.mapper.FileDataMapper;
import com.chunjae.test07.mapper.ResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private FileDataMapper fileMapper;
    @Autowired
    private ResponseMapper responseMapper;

    @Override
    public List<Board> boardList() {
        return boardMapper.boardList();
    }

    @Override
    public List<Board> boardTypeList(String boardType) {
        return boardMapper.boardTypeList(boardType);
    }

    @Override
    public BoardVO boardGet(int boardNo) {
        BoardVO board = new BoardVO();
        board.setBoard(boardMapper.boardGet(boardNo));
        if(board.getBoard().isHasFile()){
            board.setFiles(fileMapper.fileDataBoardList(boardNo));
        }
        if(board.getBoard().isHasResponse()){
            board.setResponses(responseMapper.responseBoardList(boardNo));
        }

        return board;
    }

    @Override
    public BoardVO boardRead(int boardNo) {
        boardMapper.boardVisitUpdate(boardNo);

        BoardVO board = new BoardVO();
        board.setBoard(boardMapper.boardGet(boardNo));
        if(board.getBoard().isHasFile()){
            board.setFiles(fileMapper.fileDataBoardList(boardNo));
        }
        if(board.getBoard().isHasResponse()){
            board.setResponses(responseMapper.responseBoardList(boardNo));
        }

        return board;
    }

    @Transactional
    @Override
    public int boardWrite(BoardVO board) {
        int returnNo = boardMapper.boardInsert(board.getBoard());
        if(board.getBoard().isHasFile()){
            int boardNo = boardMapper.boardGetLast();
            for(FileData f: board.getFiles()){
                f.setBoardNo(boardNo);
                fileMapper.fileDataInsert(f);
            }
        }

        return returnNo;
    }

    @Transactional
    @Override
    public int boardEdit(BoardVO board) {
        int returnNo = boardMapper.boardUpdate(board.getBoard());
        if(board.getBoard().isHasFile()){
            if(board.getFiles().size()>0){
                int boardNo = board.getBoard().getBoardNo();
                for(FileData f: board.getFiles()){
                    f.setBoardNo(boardNo);
                    fileMapper.fileDataInsert(f);
                }
            }
        }
        return returnNo;
    }

    @Transactional
    @Override
    public int boardRemove(int boardNo) {
        Board board = boardMapper.boardGet(boardNo);
        if(board.isHasFile()){
            fileMapper.fileDataBoardRemoveUpdate(boardNo);
        }
        if(board.isHasResponse()){
            responseMapper.responseBoardRemoveUpdate(boardNo);
        }
        return boardMapper.boardRemoveUpdate(boardNo);
    }

    @Override
    public int responseWrite(Response response) {
        return responseMapper.responseInsert(response);
    }

    @Override
    public int responseEdit(Response response) {
        return responseMapper.responseUpdate(response);
    }
    @Override
    public int responseRemove(int responseNo){
        return responseMapper.responseRemoveUpdate(responseNo);
    }

    @Override
    public int fileDataRemove(int fileNo) {
        return fileMapper.fileDataRemoveUpdate(fileNo);
    }

    @Override
    public int fileDataAuthority(String authority, int fileNo) {
        return fileMapper.fileDataAuthorityUpdate(authority, fileNo);
    }
}
