package applicProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Andrey on 10.11.2015.
 */
@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private UserChoiceDAO choice;

    @RequestMapping("/")
    public ModelAndView listChoices() {
        return new ModelAndView("index", "choices", choice.list());
    }

    @RequestMapping(value = "/showDB", method = RequestMethod.POST)
    public ModelAndView goBack() {return new ModelAndView("index", "choices", choice.list());}

    @RequestMapping(value = "/add_page", method = RequestMethod.POST)
    public String addPage(Model model) {
        return "add_page";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(@RequestParam(value="pattern") String pattern) {
        return new ModelAndView("index", "choices", choice.list(pattern));
    }

    @RequestMapping("/delete")
    public ModelAndView delete(@RequestParam(value="iduserChoice") long id) {
        choice.delete(id);
        return new ModelAndView("index", "choices", choice.list());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addChoice(@RequestParam(value="txtArea") String txtArea,
                               @RequestParam(value="txtField1") String txtField1,
                               @RequestParam(value="txtField2") String txtField2,
                               HttpServletRequest request,
                               HttpServletResponse response)
    {
        String chk = request.getParameter("group1");
        String[] multi = request.getParameterValues("Multi-Select");
        String res;
        if (multi != null) {
            res = multi[0];
            for (int i = 1; i < multi.length; i++) {
                res += "," + multi[i];
            }
        }
        else
            res = "";
            UserChoice ch = new UserChoice(
                    txtArea, txtField1, txtField2, chk, res);
            choice.add(ch);
            return new ModelAndView("index", "choices", choice.list());

    }

    //firstly, the idea was to get the IDs of chosen multi-select, transform them to similar int (with the help of bitwise operations
    //Then I could take th texts of the multi-selects from the table in the DB with the help of IDs, saved in similar int
    //However I could not make this idea come true because of lack of the time and experience in working with Spring technologies
    public long Transform(long chosenIDs[]){
        long res = 0;
        for (int i = 0; i < chosenIDs.length; i++) {
            res += Math.pow(2, chosenIDs[i]);
        }
        return res;
    }
}
