
import com.google.common.collect.ImmutableList;
import com.spark.test.handler.Answer;
import com.spark.test.handler.impl.ArticleListHandler;
import com.spark.test.model.Article;
import com.spark.test.model.Empty;
import com.spark.test.service.Model;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.Collections;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;


public class PostsIndexHandlerTest {

    @Test
    public void emptyListIsHandledCorrectlyInHtmlOutput() {
        Model model = EasyMock.createMock(Model.class);
        expect(model.list()).andReturn(Collections.EMPTY_LIST);
        replay(model);

        ArticleListHandler handler = new ArticleListHandler(model);
        String expectedHtml = "<body><h1>My wonderful blog</h1><div></div></body>";
        assertEquals(new Answer(200, expectedHtml), handler.process(new Empty(), Collections.emptyMap(), true));

        System.out.println("Are you kidding me?");

        verify(model);
    }

    @Test
    public void aNonEmptyListIsHandledCorrectlyInHtmlOutput() {
        Model model = EasyMock.createMock(Model.class);

        Article post1 = new Article();
        post1.setTitle("First post");
        post1.setContent("First post content");
        post1.setCategories(ImmutableList.of("Howto", "BoringPosts"));

        Article post2 = new Article();
        post2.setTitle("Second post");
        post2.setContent("Second post content");
        post2.setCategories(ImmutableList.of());

        expect(model.list()).andReturn(ImmutableList.of(post1, post2));
        replay(model);

        ArticleListHandler handler = new ArticleListHandler(model);
        String expectedHtml = "<body><h1>My wonderful blog</h1><div><div><h2>First post</h2><p>First post content</p><ul><li>Howto</li><li>BoringPosts</li></ul></div><div><h2>Second post</h2><p>Second post content</p><ul></ul></div></div></body>";
        assertEquals(new Answer(200, expectedHtml), handler.process(new Empty(), Collections.emptyMap(), true));

        verify(model);
    }
}
