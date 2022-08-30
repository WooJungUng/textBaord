package controller;

import Service.ArticleService;
import data.Article;
import data.Member;
import infra.Container;
import infra.Request;
import utils.Util;

import javax.print.attribute.standard.RequestingUserName;
import java.util.List;
import java.util.Scanner;

public class ArticleController implements  Controller{

    private Scanner sc;

    private ArticleService articleService;

    public ArticleController (){
        this.sc = Container.sc;
        this.articleService = Container.articleService;
    }

    @Override
    public void execute(Request request) {
        switch (request.getTarget()){
            case "write":
                write(request);
                break;
            case "detail":
                detail(request);
                break;
            case "delete":
                delete(request);
                break;
            case "modify":
                modify(request);
                break;
            default:
                System.out.println("존재하지 않는 요청입니다.");
                break;
        }

    }


    private void write(Request request) {


        System.out.println("== 게시글 작성 ==");

        System.out.println("제목 : ");

        System.out.println("내용 : ");

        String title = sc.nextLine().trim();

        if(title.length() == 0){
            System.out.println("제목을 입력하여 주세요.");
            return;
        }

        System.out.println("내용 : ");
        String body = sc.nextLine().trim();

        String author = request.getLogonMemberID();

        int articleId = articleService.write(title, body, author);

        System.out.println(articleId + "번 게시글이 작성되었습니다.");


    }
    public void detail(Request request){
        String paramKey = "id";

        if(!Util.hasParam(request, paramKey)){
            System.out.println(paramKey + "파라미터가 필요합니다.");
            return;
        }

        int articleId = request.getParameterIntValue(paramKey); // 1

        Article findArticle = articleService.getById(articleId);

        if(findArticle == null){
            System.out.println("해당 게시글은 존재하지 않습니다.");
            return;
        }

        System.out.println(" == " + findArticle.getId() + "번 게시글 == ");
        System.out.println("작성자 : " + findArticle.getAuthor());
        System.out.println("제목 : " + findArticle.getTitle());
        System.out.println("내용 : " + findArticle.getBody());
        System.out.println("작성일 : " + findArticle.getRegDate());

    }

    public void delete(Request request){
        String paramKey = "id";

        if(!Util.hasParam(request, paramKey)){
            System.out.println(paramKey + "파라미터가 필요합니다.");
            return;
        }
        int articleId = request.getParameterIntValue(paramKey);

        Article findArticle = articleService.getById(articleId);

        if(findArticle == null){
            System.out.println("해당 게시물은 존재하지 않습니다.");
            return;
        }

        if(!request.getLogonMemberID().equals(findArticle.getAuthor())){
            System.out.println("권한이 없습니다.");
            return ;
        }

        articleService.delete(findArticle);

        System.out.println("성공적으로 삭제되었습니다.");

    }
    private void modify(Request request) {
        String paramKey = "id";

        if(!Util.hasParam(request, paramKey)){
            System.out.println(paramKey + "파라미터가 필요합니다.");
            return;
        }
        int articleId = request.getParameterIntValue(paramKey);

        Article findArticle = articleService.getById(articleId);

        if(findArticle == null){
            System.out.println("해당 게시물은 존재하지 않습니다.");
            return;
        }

        if(!request.getLogonMemberID().equals(findArticle.getAuthor())){
            System.out.println("권한이 없습니다.");
            return ;
        }

        articleService.modify(findArticle);

        System.out.println("성공적으로 수정을 완료했습니다.");



    }



}
