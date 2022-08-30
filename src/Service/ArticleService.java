package Service;

import data.Article;
import infra.Container;
import repository.ArticleRepository;

public class ArticleService {

    private ArticleRepository articleRepository;

    public ArticleService(){
        this.articleRepository = Container.articleRepository;
    }

    public int write(String title, String body, String author) {
        return articleRepository.write(title, body, author);
    }

    public Article getById(int id) {
        return articleRepository.getById(id);
    }

    public void delete(Article article) {
        articleRepository.delete(article);
    }

    public void modify(Article findArticle) {
    }
}
