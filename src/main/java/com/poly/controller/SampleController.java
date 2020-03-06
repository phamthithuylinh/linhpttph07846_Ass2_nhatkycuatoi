package com.poly.controller;

import com.poly.model.BaiViet;
import com.poly.model.TaiKhoan;
import com.poly.service.BaiVietService;
import com.poly.service.TaiKhoanService;
import com.poly.validate.AssValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class SampleController {
    @Autowired
    private TaiKhoanService taiKhoanService;

    @Autowired
    private BaiVietService baiVietService;
    AssValidate nhatKyValidate = new AssValidate();
    TaiKhoan taiKhoan = new TaiKhoan();//tạo đối tượng taiKhoan, đối tượng sẽ lưu trữ thông tin người đăng nhập

    @GetMapping(value={"index.html","/"})
    private String index( ModelMap model){
        taiKhoanService.findAll();
        model.put("errorLogin", null);
        return "index.html";
    }

    @PostMapping("login.html")
    private String login(HttpSession session, @RequestParam("tenTaiKhoan") String user, @RequestParam("matKhau") String password,
                         ModelMap model) {
        if (session.getAttribute("userLogin") != null) {
            return "redirect:/";
        } else {
            //thông tin tài khoản đăng nhập sẽ được truyền vào đối tượng taiKhoan
            taiKhoan = taiKhoanService.findByUsernameAndPassword(user,password);
            System.out.println(user+"  "+password);
            if(taiKhoan!=null){
                session.setAttribute("account",taiKhoan);
                return "redirect:/view.html";
            }else{
                model.put("errorLogin", "Sai Tai Khoan Hoac Mat Khau!");
                return  "index.html";
            }
        }
    }
    //view
    @GetMapping("view.html")
    public ModelAndView listProvinces(){

        if(taiKhoan==null || taiKhoan.getTenDangNhap()==null){
            ModelAndView modelAndView = new ModelAndView("/index");
            modelAndView.addObject("errorLogin","Vui Long Dang Nhap !");
            return modelAndView;
        }

        List<BaiViet> baiViet = baiVietService.findAllBaiVietByTenDangNhap(taiKhoan.getTenDangNhap());
        ModelAndView modelAndView = new ModelAndView("/view");
//        modelAndView.addObject("taiKhoan", taiKhoan);
        modelAndView.addObject("baiViet", baiViet);
        return modelAndView;
    }
    //đăng ký tài khoản
    @GetMapping(value={"registration.html"})
    private ModelAndView showRegistration(){
        ModelAndView modelAndView = new ModelAndView("/registration");
        modelAndView.addObject("taiKhoan", new TaiKhoan());
        return modelAndView;
    }

    @PostMapping("registration.html")
    private ModelAndView registration(@ModelAttribute("taiKhoan") TaiKhoan taiKhoan, @RequestParam("nhapLaiMatKhau")String nhapLaiMatKhau) {
        System.out.println(nhapLaiMatKhau);

        if (!nhatKyValidate.checkSize(1, 50, taiKhoan.getTenDangNhap().length())) {
            ModelAndView modelAndView = new ModelAndView("/registration");
            modelAndView.addObject("taiKhoan", new TaiKhoan());
            modelAndView.addObject("errorRegistration", "Tên đăng nhập từ 1-50 ký tự");
            return modelAndView;
        } else if (!nhatKyValidate.checkSize(6, 20, taiKhoan.getMatKhau().length())) {
            ModelAndView modelAndView = new ModelAndView("/registration");
            modelAndView.addObject("taiKhoan", new TaiKhoan());
            modelAndView.addObject("errorRegistration", "Mật khẩu từ 6-20 ký tự");
            return modelAndView;
        } else if (!nhatKyValidate.checkSDT(taiKhoan.getSDT())||taiKhoan.getSDT().length()!=10){
            ModelAndView modelAndView = new ModelAndView("/registration");
            modelAndView.addObject("taiKhoan", new TaiKhoan());
            modelAndView.addObject("errorRegistration", "SDT không đúng định dạng!");
            return modelAndView;

        }else if (!taiKhoan.getMatKhau().equals(nhapLaiMatKhau)) {
            ModelAndView modelAndView = new ModelAndView("/registration");
            modelAndView.addObject("taiKhoan", new TaiKhoan());
            modelAndView.addObject("errorRegistration", "Mật khẩu không khớp, vui lòng nhập lại!");
            return modelAndView;

        }
         else

            System.out.println(taiKhoan.toString());
            taiKhoanService.save(taiKhoan);
            ModelAndView modelAndView = new ModelAndView("/registration");
            modelAndView.addObject("taiKhoan",new TaiKhoan());
            modelAndView.addObject("errorRegistration","Dang Ky Thanh Cong !");
            return modelAndView;
    }

    //tạo bài viết
    @GetMapping("create.html")
    private ModelAndView showTaoBaiViet(){

        if(taiKhoan==null || taiKhoan.getTenDangNhap()==null){
            ModelAndView modelAndView = new ModelAndView("/index");
            modelAndView.addObject("errorLogin","Vui Long Dang Nhap !");
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("baiViet", new BaiViet());
        return modelAndView;
    }
    @PostMapping("create.html")
    private ModelAndView taoBaiViet(@ModelAttribute("baiViet") BaiViet baiViet){
        if (!nhatKyValidate.checkSize(1, 50, baiViet.getTieuDe().length())) {
            ModelAndView modelAndView = new ModelAndView("/create");
            modelAndView.addObject("baiViet", new BaiViet());
            modelAndView.addObject("message", "Độ dài tiêu đề từ 1-50 ký tự");
            return modelAndView;
        } else if (!nhatKyValidate.checkSize(1, 50, baiViet.getTamTrang().length())) {
            ModelAndView modelAndView = new ModelAndView("/create");
            modelAndView.addObject("baiViet", new BaiViet());
            modelAndView.addObject("message", "Độ dài tâm trạng từ 1-30 ký tự");
            return modelAndView;
        } else if (!nhatKyValidate.checkSize(1, 5000, baiViet.getNoiDung().length())) {
            ModelAndView modelAndView = new ModelAndView("/create");
            modelAndView.addObject("baiViet", new BaiViet());
            modelAndView.addObject("message", "Độ dài nội dung từ 1-2000 ký tự");
            return modelAndView;
        } else if (!nhatKyValidate.checkSize(1, 500, baiViet.getAnh().length())) {
            ModelAndView modelAndView = new ModelAndView("/create");
            modelAndView.addObject("baiViet", new BaiViet());
            modelAndView.addObject("message", "Độ dài đường dẫn ảnh từ 1-500 ký tự");
            return modelAndView;
        } else

        baiViet.setTenDangNhap(taiKhoan.getTenDangNhap());
        System.out.println(baiViet.toString());
        baiVietService.save(baiViet);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("baiViet", new BaiViet());
        modelAndView.addObject("message", "Tao Bai Dang Thanh Cong!");
        return modelAndView;
    }


    //xóa bài viết

    Optional<BaiViet> baiViet1 = Optional.of(new BaiViet());
    @GetMapping("deletePost/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){

        if(taiKhoan==null || taiKhoan.getTenDangNhap()==null){
            ModelAndView modelAndView = new ModelAndView("/index");
            modelAndView.addObject("errorLogin","Vui Long Dang Nhap !");
            return modelAndView;
        }

        baiViet1 = baiVietService.findById(id);
        if(baiViet1 != null) {
            ModelAndView modelAndView = new ModelAndView("/deletePost");
            modelAndView.addObject("baiViet",new BaiViet());
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("deletePost")
    public String deletePost() {
        System.out.println(baiViet1.get().getMaBaiViet());
        baiVietService.remove(baiViet1.get().getMaBaiViet());
        return "redirect:view.html";
    }

    //sửa bài viết
    Long maBaiViet;//lưu trữ mã bài viết được lấy từ danh  sách

    @GetMapping("/editPost/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){

        if(taiKhoan==null || taiKhoan.getTenDangNhap()==null){
            ModelAndView modelAndView = new ModelAndView("/index");
            modelAndView.addObject("errorLogin","Vui Long Dang Nhap !");
            return modelAndView;
        }

        baiViet1 = baiVietService.findById(id);
        maBaiViet=baiViet1.get().getMaBaiViet();
        System.out.println(maBaiViet);
        if(baiViet1 != null) {
            ModelAndView modelAndView = new ModelAndView("/editPost");
            modelAndView.addObject("baiViet", baiViet1);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/editPost")
    public ModelAndView updateProvince(@ModelAttribute("baiViet") BaiViet baiViet){
        if (!nhatKyValidate.checkSize(1, 50, baiViet.getTieuDe().length())) {
            ModelAndView modelAndView = new ModelAndView("/create");
            modelAndView.addObject("baiViet", new BaiViet());
            modelAndView.addObject("message", "Độ dài tiêu đề từ 1-50 ký tự");
            return modelAndView;
        } else if (!nhatKyValidate.checkSize(1, 50, baiViet.getTamTrang().length())) {
            ModelAndView modelAndView = new ModelAndView("/create");
            modelAndView.addObject("baiViet", new BaiViet());
            modelAndView.addObject("message", "Độ dài mô tả từ 1-50 ký tự");
            return modelAndView;
        } else if (!nhatKyValidate.checkSize(1, 5000, baiViet.getNoiDung().length())) {
            ModelAndView modelAndView = new ModelAndView("/create");
            modelAndView.addObject("baiViet", new BaiViet());
            modelAndView.addObject("message", "Độ dài nội dung từ 1-5000 ký tự");
            return modelAndView;
        } else if (!nhatKyValidate.checkSize(1, 500, baiViet.getAnh().length())) {
            ModelAndView modelAndView = new ModelAndView("/create");
            modelAndView.addObject("baiViet", new BaiViet());
            modelAndView.addObject("message", "Độ dài đường dẫn ảnh từ 1-500 ký tự");
            return modelAndView;
        } else
        baiViet.setTenDangNhap(taiKhoan.getTenDangNhap());
        baiViet.setMaBaiViet(maBaiViet);
        System.out.println(baiViet.toString());
        baiVietService.save(baiViet);
//        baiVietService.remove(maBaiViet);
        ModelAndView modelAndView = new ModelAndView("/editPost");
        modelAndView.addObject("baiViet", baiViet);
        modelAndView.addObject("message", "Sua Bai Viet Thanh Cong!");
        return modelAndView;
    }
    //đăng xuất
    @GetMapping("logout")
    public ModelAndView logout( ModelMap model){
        taiKhoan=null;
        ModelAndView modelAndView = new ModelAndView("/index");
        model.put("errorLogin", "Da Dang Xuat Thanh Cong!");
        return modelAndView;

    }
    //sửa thông tin tài khoản
    @GetMapping("/infor.html")
    private ModelAndView showEditInfor(){

        if(taiKhoan==null || taiKhoan.getTenDangNhap()==null){
            ModelAndView modelAndView = new ModelAndView("/index");
            modelAndView.addObject("errorLogin","Vui Long Dang Nhap !");
            return modelAndView;
        }

            taiKhoan.setMatKhau("");
            ModelAndView modelAndView = new ModelAndView("/infor");
            modelAndView.addObject("taiKhoan1", taiKhoan);
            return modelAndView;
    }
    @PostMapping("/infor.html")
    private ModelAndView editInfor(@ModelAttribute("taiKhoan1") TaiKhoan taiKhoan1){
        
        System.out.println(taiKhoan1.toString());
        taiKhoan1.setTenDangNhap(taiKhoan.getTenDangNhap());
        taiKhoanService.save(taiKhoan1);
//        baiVietService.remove(maBaiViet);
        ModelAndView modelAndView = new ModelAndView("/infor");
        modelAndView.addObject("taiKhoan1", taiKhoan1);
        modelAndView.addObject("message", "Sua Thong Tin Thanh Cong!");
        return modelAndView;
    }


    }
