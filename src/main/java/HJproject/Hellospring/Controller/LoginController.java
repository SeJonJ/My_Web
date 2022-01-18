//package HJproject.Hellospring.Controller;
//
//import HJproject.Hellospring.Session.SessionManager;
//import HJproject.Hellospring.domain.member.Member;
//import HJproject.Hellospring.domain.login.LoginForm;
//import HJproject.Hellospring.service.LoginService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Slf4j
//@Controller
//@RequiredArgsConstructor // 클래스의 final 필드에 대한 생성자를 자동으로 생성
//public class LoginController {
//
//    private final LoginService loginService;
//    private final SessionManager sessionManager; // @Component 필요
//
//    @GetMapping("/login")
//    public String LoginForm(@ModelAttribute("LoginForm")LoginForm form){
//        return "members/login";
//    }
//
//    /* @PostMapping("/login") : 세션 없이 쿠키로만 로그인 */
//    /* HttpServletResponse : 아래의 쿠키값을 생성 후 클라이언트에게 보낼때 response 에 넣어서 보내야함 */
//    public String login(@ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse httpServletResponse) {
//        if (bindingResult.hasErrors()) {
//            return "members/login";
//        }
//
//        // loginForm 을 통해 값들을 가져옴
//        Member LoginMember = loginService.login(form.getLoginid(), form.getLoginpw());
//
//        if (LoginMember == null) { // login 메서드에서 던져주는 값이 null 이면 로그인 실패
////            bindingResult.reject("login Fail", "아이디 또는 비밀번호가 맞지 않습니다");
//            System.out.println("로그인 실패");
//            return "members/login";
//        }
//
//        // null 이외의 값 즉 member 객체라면 로그인 성공 처리
//        // 쿠키에 시간 정보를 주지 않았기 때문에 세션 쿠키로 인식됨 -> 브라우저 종료시 모두 종료
//        Cookie CookieCode = new Cookie("memberCode", String.valueOf(LoginMember.getCode()));
//        httpServletResponse.addCookie(CookieCode);
//        System.out.println("쿠키 정보 전달 완료 : "+ CookieCode);
//        return "redirect:/";
//    }
//
//    // @PostMapping("/logout")
//    // 쿠키를 삭제하려면 쉽게 그냥 쿠키 생명주기, 시간을 0으로 만들어 버리면 됨
//    public String logout(HttpServletResponse httpServletResponse){
//        Cookie cookie = new Cookie("memberCode", null);
//        cookie.setMaxAge(0);
//        httpServletResponse.addCookie(cookie);
//        return "redirect:/";
//    }
//
////    @PostMapping("/login") // 직접 만든 세션을 활용한 로그인
//    public String loginWithSession(@ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse httpServletResponse) {
//        if (bindingResult.hasErrors()) {
//            return "members/login";
//        }
//
//        // loginForm 을 통해 값들을 가져옴
//        Member LoginMember = loginService.login(form.getLoginid(), form.getLoginpw());
//
//        if (LoginMember == null) { // login 메서드에서 던져주는 값이 null 이면 로그인 실패
////            bindingResult.reject("login Fail", "아이디 또는 비밀번호가 맞지 않습니다");
//            System.out.println("로그인 실패");
//            return "members/login";
//        }
//
//        // null 이외의 값 즉 member 객체라면 로그인 성공 처리
//        // createSession 메서드에 value : LoginMember 과 응답 : httpServletResponse 넘겨주면서 세션을 생성
//        sessionManager.createSession(LoginMember, httpServletResponse);
//        return "redirect:/";
//    }
//
////    @PostMapping("/logout") // 직접 만든 세션을 활용한 로그아웃
//    // 세션 종료를 위해서는 sessionManager 에 만들어두었던 expireCookie 를 사용하자
//    public String logoutWithSession(HttpServletRequest request){
//        sessionManager.expireCookie(request);
//        return "redirect:/";
//    }
//
//}
