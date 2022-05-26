package in.post.service;

import java.util.List;

import in.post.dto.PostDTO;

public interface PostService {
	//전체 조회
	public List<PostDTO> postSelectAll();
	//특정 게시글 조회
	public PostDTO postSelect(int postnum);
	
	public PostDTO postSelectWriter(String postwriter);
	//게시글 삭제
	public PostDTO postDelete(int postnum);
	//게시글 작성
	public void postInsert(PostDTO postDTO);
	//게시글 수정
	public PostDTO postUpdate(PostDTO postDTO);

	public PostDTO postSelectMaxView();
}
