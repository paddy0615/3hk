package com.example.demo.service;

import com.example.demo.bean.*;
import com.example.demo.dao.*;
import com.example.demo.entity.EformEntity;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Service("eformService")
public class EformService {
    @Value("${spring.profiles.active}")
    private String active; //读取配置文件中的参数

    @Value("${spring.mail.username}")
    private String Sender; //读取配置文件中的参数

    private  static Logger logger = LoggerFactory.getLogger(EformService.class);

    @Resource
    E_certificateDao e_certificateDao;

    @Resource
    E_area_nameDao e_area_nameDao;

    @Resource
    E_form_resultDao e_form_resultDao;

    @Resource
    E_form_typeDao e_form_typeDao;

    @Resource
    EformDao eformDao;

    @Resource
    EformEntityDao eformEntityDao;

    @Resource
    LanguageDao languageDao;

    @Resource
    private JavaMailSender mailSender; //自动注入的Bean

    @Resource
    private TemplateEngine templateEngine;

    public List<E_area_name> getAreaNames(){
        return e_area_nameDao.getAllOrderByupdateDate();
    }

    public List<E_certificate> getCertificates(){
        return e_certificateDao.findAll();
    }


    /**
     * 添加eform
     * @param eform
     */
    @Transactional
    public void save(Eform eform){
        eformDao.save(eform);
    }

    /**
     * 添加e_form_resultDao
     * @param e_form_result
     */
    @Transactional
    public void saveResult(E_form_result e_form_result){
        e_form_resultDao.save(e_form_result);
    }

    /**
     * 获取Language
     */
    public Language getLanguage(long i) throws Exception {
        return languageDao.findById(i);
    }

    /**
     * 获取Language
     */
    public List<Language> findAllLanguage(){
        return languageDao.findAll();
    }

    /**
     * 获取随机数
     * @return
     */
    public int getRandom(){
        int i = (int)(Math.random()*1000000000);
        if(eformDao.countByRandom(i) > 0 ) {
            return getRandom();
        }else{
            return i;
        }

    }

    /**
     * 获取getMailType
     */
    public String getMailType(String type,long langid,String pnr) throws Exception {
        E_form_type e_form_type = e_form_typeDao.findById(Long.parseLong(type));
        Language language =  languageDao.findById(langid);
        return "Smart Form/"+language.getTitle()+"/"+e_form_type.getEn()+"/"+pnr;
    }

    /**
     * 获取e_certificate 标题
     */
    public String getCertificateTitle(long id) throws Exception {
        String s = "";
        if(id > 0){
            E_certificate certificate = e_certificateDao.findById(id);
            s = certificate.getEn();
        }
        return s;
    }

    /**
     * 获取getMailUserType
     */
    public String getMailUserType(String langid){
        String s = "";
        switch(langid){
            case "1" :
                s = "電子表格郵件確認";
                break;
            case "2"  :
                s = "电子表格邮件确认";
                break;
            case "4"  :
                s = "表計算ドキュメントのメール確認";
                break;
            case "5"  :
                s = "환불 요청 메일 확인서";
                break;
            default :
                s = "Smart Form Acknowledgement";
        }
        return s;
    }

    /**
     * 发确认邮件
     * @throws
     */
    public void sendSimpleMailUser(Map<String, Object> valueMap) throws Exception {
        String sender = "Soniccs.Guest.Relations@hkexpress.com";
        String password = "H+==2GaR";
        // 收件人邮箱地址
        String receiver = valueMap.get("To").toString();
        // office365 邮箱服务器地址及端口号
        String host = "smtp-mail.outlook.com";
        String prot = "587";
        try{
            Properties props = new Properties();
            // 发送服务器需要身份验证
            props.setProperty("mail.smtp.auth", "true");
            // 设置邮件服务器主机名
            props.setProperty("mail.host", host);
            // 发送邮件协议名称
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.smtp.port", prot);
            props.setProperty("mail.smtp.starttls.enable", "true");
            // 设置环境信息
            Session session = Session.getInstance(props);
            // 创建邮件对象
            MimeMessage msg = new MimeMessage(session);
            // 设置邮件内容
            MimeMessageHelper helper = new MimeMessageHelper(msg, true,"UTF-8");
            // 设置发件人邮箱
            helper.setFrom("guest.relations@hkexpress.com");
            // 设置收件人邮箱
            helper.setTo(receiver);
            // 设置邮件标题
            helper.setSubject(valueMap.get("title").toString());
            Context context = new Context();
            context.setVariables(valueMap);
            String content = this.templateEngine.process("faqs/eFormMailGuest.html", context);
            helper.setText(content, true);
            org.springframework.core.io.Resource resource = new ClassPathResource("static/img/faq_top3.png");
            // 图片
            FileSystemResource file = new FileSystemResource(resource.getFile());
            helper.addInline("faq_top3", file);
            Transport transport = session.getTransport();
            // 连接邮件服务器
            transport.connect(sender, password);
            // 发送邮件
            transport.sendMessage(msg, new Address[]{new InternetAddress(receiver)});
            // 关闭连接
            transport.close();
        }catch( Exception e ){
            e.printStackTrace();
        }
    }

    /**
     * 发邮件
     * @throws Exception
     */
    public void sendSimpleMail(Map<String, Object> valueMap) throws Exception {
        MimeMessage mimeMessage = null;
        mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        // 设置发件人邮箱
        helper.setFrom(Sender);
        if("pro".equals(active)){
            // 设置收件人邮箱- 此邮件已关联zoho.
            helper.setTo("guest.relations@hkexpress.com");
            // 抄送邮件接收人
            helper.setCc(Sender);
        }else{
            // 设置收件人邮箱
            helper.setTo("windy.tam@sonic-teleservices.com");
            // 抄送邮件接收人
            helper.setCc(new String[]{Sender,"sarsi.pablo@sonic-teleservices.com","erica.yu@sonic-teleservices.com","gary.lam@sonic-callcenter.com","cecile.agbing@sonic-teleservices.com","emerson.bautista@sonic-teleservices.com","sisi.yip@sonic-callcenter.com"});

        }
        // 设置邮件标题
        helper.setSubject(valueMap.get("title").toString());
        Context context = new Context();
        context.setVariables(valueMap);
        String content = this.templateEngine.process("faqs/eFormMail.html", context);
        helper.setText(content, true);
        org.springframework.core.io.Resource resource = new ClassPathResource("static/img/faq_top4.png");
        // 图片
        FileSystemResource file = new FileSystemResource(resource.getFile());
        helper.addInline("faq_top4", file);
        // 添加附件
        //String fileName = f.substring(f.lastIndexOf(File.separator));
        //helper.addAttachment(fileName, fileSystemResource);
        // 发送邮件
        mailSender.send(mimeMessage);
    }


    /**
     * 对接PNR接口
     * @param eform
     * @return
     */
    public String getBookingAPI(Eform eform,E_form_result result) throws Exception{
        String uri="http://sonicinternal.callsonic.com/Balch/getBookingAPI.action?channel=eform";
        if("pro".equals(active)){
            uri="http://www.callsonic.com/Balch_ver02/getBookingAPI.action?channel=eform";
        }
        uri += "&pnr="+eform.getPnr();
        uri += "&firstname="+eform.getFirstname();
        uri += "&lastname="+eform.getLastname();
        uri += "&email="+eform.getEmail();
        System.out.println(uri);
        logger.info(uri);
        //  模拟请求
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        RestTemplate restTemplate=new RestTemplate();
        String strbody=restTemplate.exchange(uri, HttpMethod.GET, entity,String.class).getBody();
        Document doc = DocumentHelper.parseText(strbody);
        // 获取根节点
        Element rootElt = doc.getRootElement(); // 获取根节点
        String s = rootElt.elementTextTrim("State");
        result.setResult(s);
        result.setResultxml(strbody);
        return s;
    }

    /**
     * 后台- 初始化数据
     * @param langId
     * @param type
     * @param startTime
     * @param endTime
     * @param pageable
     * @return
     */
    public Page<EformEntity> getEformEntityPage(long langId, long type, String startTime, String endTime,String searchTest, Pageable pageable){
        return eformEntityDao.getEformEntityPage(langId,type,startTime,endTime,searchTest,pageable);
    }


    /**
     * 获取E_form_type
     */
    public List<E_form_type> findAllFormType(){
        return e_form_typeDao.findAll();
    }

    /**
     * 按ID获取E_from
     */
    public Eform findEformById(long id){
        return eformDao.findById(id);
    }
}
