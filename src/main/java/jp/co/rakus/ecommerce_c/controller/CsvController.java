package jp.co.rakus.ecommerce_c.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommerce_c.domain.User;
import jp.co.rakus.ecommerce_c.repository.UserRepository;

@Controller
public class CsvController {
	@Autowired
	private UserRepository userRepository;

	/**
	 * ユーザー情報csv形式でダウンロードする
	 */
	@RequestMapping("/csvUserDownload")
	public void csvDownload(HttpServletResponse response) {

		// 文字コードと出力するCSVファイル名を設定
		response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE + ";charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"test.csv\"");

		// try-with-resources文を使うことでclose処理を自動化
		try (PrintWriter pw = response.getWriter()) {
			List<User> all_users = userRepository.findAll();
			for (int i = 0; i < all_users.size(); i++) {
				long id = all_users.get(i).getId();
				String name = all_users.get(i).getName();
				String email = all_users.get(i).getEmail();
				String password = all_users.get(i).getPassword();
				String address = all_users.get(i).getAddress();
				String telephone = all_users.get(i).getTelephone();
				

				// CSVファイル内部に記載する形式で文字列を設定
				String outputString = id + "," + name + "," + email + "," + password + "," + address + "," + telephone
						+ "\r\n";

				// CSVファイルに書き込み
				pw.print(outputString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/csvPurchaseDownload")
	public void csvPurchaseDownload(HttpServletResponse response) {

		// 文字コードと出力するCSVファイル名を設定
		response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE + ";charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"test.csv\"");

		// try-with-resources文を使うことでclose処理を自動化
		try (PrintWriter pw = response.getWriter()) {
			List<User> all_users = userRepository.findAll();
			for (int i = 0; i < all_users.size(); i++) {
				long id = all_users.get(i).getId();
				String name = all_users.get(i).getName();
				String email = all_users.get(i).getEmail();
				String password = all_users.get(i).getPassword();
				String address = all_users.get(i).getAddress();
				String telephone = all_users.get(i).getTelephone();
				

				// CSVファイル内部に記載する形式で文字列を設定
				String outputString = id + "," + name + "," + email + "," + password + "," + address + "," + telephone
						+ "\r\n";

				// CSVファイルに書き込み
				pw.print(outputString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
