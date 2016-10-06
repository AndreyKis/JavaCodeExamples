package Project.Controllers;

import Project.CRUD.BookActions;
import Project.Entities.BookEntity;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by User on 18.03.2016.
 */
@Controller
@RequestMapping("/")
public class AddPageController {
    //@Autowired
    BookActions books = new BookActions();


    //response useful for 403 response (forbidden to client)
    @RequestMapping(value = "/add", method = RequestMethod.POST,
            headers = "content-type=application/x-www-form-urlencoded; charset=UTF-8",
            consumes = "application/json",
            produces = "application/json; charset=UTF-8")
    @ResponseBody //which is returned, will be displayed on screen
    public String listOfBooks(@RequestParam(value="bookName", required = false) String bookName,
                                    @RequestParam(value="bookAuthor") String bookAuthor,
                                    @RequestParam(value="bookGenre") String bookGenre)
                                    /*@RequestBody String requestBody,
                                    HttpServletResponse response) */{
        /*BookEntity bookToAdd = new BookEntity();//bookName, bookAuthor, bookGenre);
        books.AddBook(bookToAdd);
        return new ModelAndView("index", "books", books.GetListOfBooks());*/
        JSONObject jsonObj = new JSONObject();

        if(bookName == null)
            jsonObj.put("isBookName", false);
        else
        jsonObj.put("isBookName", true);


        //JSONObject jsonObj2 = new JSONObject(requestBody);
        /*if(jsonObj2.has("bookName")) {
            bookName = jsonObj2.getString("bookName");
            jsonObj.put("isBookName", true);
        }
        else {
            bookName = "noBookName";
            jsonObj.put("isBookName", false);
        }*/

        return jsonObj.toString();
    }

    @RequestMapping(value = "/showDB", method = RequestMethod.POST)
    public ModelAndView goBack() {
        return new ModelAndView("index", "books", books.GetListOfBooks());
    }
}
