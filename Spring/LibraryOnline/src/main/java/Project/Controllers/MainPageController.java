package Project.Controllers;

import Project.CRUD.BookActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by User on 16.03.2016.
 */
@Controller
@RequestMapping("/")
public class MainPageController {
    //@Autowired
    BookActions books = new BookActions();

    @RequestMapping("/")
    public ModelAndView listOfBooks()
    {
        ArrayList booksList = books.GetListOfBooks();
        return new ModelAndView("index", "books", booksList);
    }

    @RequestMapping(value = "/add_page", method = RequestMethod.POST)
    public String addPage(Model model) {
        return "add_page";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(@RequestParam(value="pattern") String pattern) {
        return new ModelAndView("index", "books", books.GetListOfBooks(pattern));
    }

    @RequestMapping("/delete")
    public ModelAndView delete(@RequestParam(value="bookId") long id) {
        books.DeleteBook(id);
        return new ModelAndView("index", "books", books.GetListOfBooks());
    }
}
