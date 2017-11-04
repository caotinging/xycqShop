package caotinging.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import caotinging.domain.Category;
import caotinging.domain.Product;
import caotinging.service.AdminService;
import caotinging.utils.BeanFactory;
import caotinging.utils.RandomID;

public class AdminFileUploadServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product product = new Product();
		Map<String, Object> productMap = new HashMap<String, Object>();
		
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024 * 1024 * 10);
			String path_cache = this.getServletContext().getRealPath("upload/cache");
			factory.setRepository(new File(path_cache));

			ServletFileUpload fileUpload = new ServletFileUpload(factory);

			if (ServletFileUpload.isMultipartContent(request)) {
				fileUpload.setHeaderEncoding("UTF-8");

				@SuppressWarnings("unchecked")
				List<FileItem> fileItemList = fileUpload.parseRequest(request);
				
				for(FileItem fileItem: fileItemList) {
					if(fileItem.isFormField()) {
						String fieldName = fileItem.getFieldName();
						String fieldValue = fileItem.getString("UTF-8");
						
						productMap.put(fieldName, fieldValue);
					}else {
						//图片上传文件项
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
				product.setPid(RandomID.getRandomID());
				product.setPflag(0);
				product.setPdate(new Date());
				
				Category category = new Category();
				category.setCid(productMap.get("cid").toString());
				product.setCategory(category);
				
				AdminService service = (AdminService) BeanFactory.getBean("adminService");
				boolean issuccess = service.storeProduct(product);
				
				if(issuccess) {
					response.sendRedirect(request.getContextPath()+"/admin/product/list.jsp");
				} else {
					response.sendRedirect(request.getContextPath()+"/error.jsp");
				}
			}
		} catch (FileUploadException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
