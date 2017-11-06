package caotinging.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import caotinging.domain.Category;
import caotinging.domain.Product;
import caotinging.service.AdminService;
import caotinging.utils.BeanFactory;

public class AdminFileUploadExitServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product product = new Product();
		Map<String, Object> productMap = new HashMap<String, Object>();
		
		if(ServletFileUpload.isMultipartContent(request)) {
			try{
				String path_cache = this.getServletContext().getRealPath("upload/cache");
				DiskFileItemFactory factory = new DiskFileItemFactory(1024*1024, new File(path_cache));
				ServletFileUpload fileUpload = new ServletFileUpload(factory);
				
				fileUpload.setHeaderEncoding("UTF-8");
				@SuppressWarnings("unchecked")
				List<FileItem> fileItemList = fileUpload.parseRequest(request);
				for(FileItem fileItem: fileItemList) {
					if(fileItem.isFormField()) {
						String fieldName = fileItem.getFieldName();
						String fieldValue = fileItem.getString("UTF-8");
						productMap.put(fieldName, fieldValue);
					}else{
						String fileName = fileItem.getName();
						InputStream input = fileItem.getInputStream();
						String path_upload = this.getServletContext().getRealPath("upload");
						OutputStream output = new FileOutputStream(path_upload+"/"+fileName);
						IOUtils.copy(input, output);
						
						input.close();
						output.close();
						fileItem.delete();
						
						productMap.put("pimage", "upload/"+fileName);
					}
				}
				
				BeanUtils.populate(product, productMap);
				Category category = new Category();
				category.setCid(productMap.get("cid").toString());
				product.setCategory(category);
				
				AdminService service = (AdminService) BeanFactory.getBean("adminService");
				boolean isSuccess = service.exitProductInfo(product);
				
				if(isSuccess) {
					response.sendRedirect(request.getContextPath()+"/admin/product/list.jsp");
				}else {
					response.sendRedirect(request.getContextPath()+"/error.jsp");
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
