package org.ndshop.user.common.ctrl;

import com.dounine.corgi.commons.ResponseText;
import com.dounine.corgi.spring.rpc.Reference;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.user.login.service.IUserAuthCodeSer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-28 17:28]
 * @Description: [验证码]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@RestController
@RequestMapping("showAuthCode")
public class AuthCodeCtrl {
    @Reference
    private IUserAuthCodeSer userAuthCodeSer;

    /**
     * 是否需要验证码
     * @param account 账号
     * @return
     * @throws SerException
     */
    @GetMapping("showAuthCode")
    public ResponseText showAuthCode(String account) throws SerException {
        return new ResponseText(userAuthCodeSer.showAuthCode(account));
    }

    /**
     * 生成验证码
     * @param account 账号
     * @param response
     * @throws SerException
     */
    @GetMapping("generateCode")
    public void generateCode(String account, HttpServletResponse response) throws SerException {
        try {
            response.setContentType("image/jpeg");
            response.setDateHeader("Expires", -1);
            response.setHeader("Cache-Control", "no-cache");
            BufferedImage image = userAuthCodeSer.generateCode(account);
            // 8.将图片写给浏览器
            try {
                ImageIO.write(image, "jpg", response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {

        }
    }

}
