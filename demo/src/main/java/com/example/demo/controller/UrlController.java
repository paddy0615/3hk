package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/*
 * url跳转页面
 * paddy 2018/9/17
 * */
@Controller
@RequestMapping("appPage")
public class UrlController {
    @RequestMapping("/")
    public String index(){return "faqs/index3hk";}

    @RequestMapping("/index")
    public String index1(){return "faqs/index3hk";}

    @RequestMapping("/search")
    public String search(){return "faqs/search";}

    @RequestMapping("/indexDetailed")
    public String indexDetailed(){return "faqs/indexDetailed";}

    @RequestMapping("/admin/login")
    public String login(){return "faqs/admin/login";}

    @RequestMapping("/goLogin")
    public String goLogin(){
        return "redirect:/appPage/admin/login";
    }

    @RequestMapping("/admin/category")
    public String category(){
        return "faqs/admin/category";
    }

    @RequestMapping("/admin/categoryEdit")
    public String categoryEdit(){
        return "faqs/admin/categoryEdit";
    }

    @RequestMapping("/admin/languageEdit")
    public String languageEdit(){
        return "faqs/admin/languageEdit";
    }

    @RequestMapping("/admin/language")
    public String language(){
        return "faqs/admin/language";
    }

    @RequestMapping("/admin/detailed")
    public String detailed(){
        return "faqs/admin/detailed";
    }

    @RequestMapping("/admin/detailedEdit")
    public String detailedEdit(){
        return "faqs/admin/detailedEdit";
    }

    @RequestMapping("/admin/feedback")
    public String feedback(){
        return "faqs/admin/feedback";
    }

    @RequestMapping("/admin/feedbackSet")
    public String feedbackSet(){
        return "faqs/admin/feedbackSet";
    }

    @RequestMapping("/admin/notags")
    public String notags(){
        return "faqs/admin/notags";
    }

    @RequestMapping("/admin/faqOne")
    public String faqOne(){
        return "faqs/admin/faqOne";
    }

    @RequestMapping("/admin/faqOneEdit")
    public String faqOneEdit(){
        return "faqs/admin/faqOneEdit";
    }

    @RequestMapping("/admin/faqTwo")
    public String faqTwo(){return "faqs/admin/faqTwo";}

    @RequestMapping("/admin/faqThree")
    public String faqThree(){
        return "faqs/admin/faqThree";
    }

    @RequestMapping("/admin/eForm")
    public String eForm(){
        return "faqs/admin/eForm";
    }

    @RequestMapping("/admin/eFormSet")
    public String eFormSet(){
        return "faqs/admin/eFormSet";
    }

    /**
     * Request for itinerary 重新發送行程單
     * @return
     */
    @RequestMapping("/RequestForItinerary")
    public String eForm1(){return "faqs/eForm1";}

    /**
     * Duplicate Booking 重復訂單
     * @return
     */
    @RequestMapping("/DuplicateBooking")
    public String eForm2(){return "faqs/eForm2";}

    /**
     * Request for Certificate   證明申請
     * @return
     */
    @RequestMapping("/RequestForCertificate")
    public String eForm3(){return "faqs/eForm3";}

    /**
     * Name Correction    姓名修正
     * @return
     */
    @RequestMapping("/NameCorrection")
    public String eForm4(){return "faqs/eForm4";}


    /**
     * Payment Failure   支付失敗
     * @return
     */
    @RequestMapping("/PaymentFailure")
    public String eForm5(){return "faqs/eForm5";}

    /**
     * Reconfirm Flight 確認航班
     * @return
     */
    @RequestMapping("/ReconfirmFlight")
    public String eForm6(){return "faqs/eForm6";}

    @RequestMapping(value="/eForm{id}",method= RequestMethod.GET)
    public String eFormIndex(HttpServletRequest request, @PathVariable("id") String id,
                             @RequestParam(name = "langId",defaultValue = "6",required = true) long langId
                            ,@RequestParam(name = "dlId",defaultValue = "0",required = true) long dlId){
        String t = "";
        if("1".equals(id)){
            t = "RequestForItinerary";
        }else if("2".equals(id)){
            t = "DuplicateBooking";
        }else if("3".equals(id)){
            t = "RequestForCertificate";
        }else if("4".equals(id)){
            t = "NameCorrection";
        }else if("5".equals(id)){
            t = "PaymentFailure";
        }else if("6".equals(id)){
            t = "ReconfirmFlight";
        }
        return "redirect:/appPage/"+t+"?langId="+langId+"&dlId="+dlId;
    }

}
