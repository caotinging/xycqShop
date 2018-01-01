package caotinging.domain;

import caotinging.domain.Noticebill;
import caotinging.domain.Staff;
import java.sql.Timestamp;


/**
 * Workbill entity. @author MyEclipse Persistence Tools
 */

public class Workbill  implements java.io.Serializable {
	
	//取件状态
	public static final String PICKSTATE_NONE = "未取件";
	public static final String PICKSTATE_RUN = "取件中";
	public static final String PICKSTATE_YEAH = "已揽件";
	
	//工单类型
	public static final String TYPE_FAST = "加急件";
	public static final String TYPE_COMMON = "普通件";
	public static final String TYPE_RISH = "贵重件";	
    // Fields    

     private String id;
     private Noticebill noticebill;
     private Staff staff;
     private String type;
     private String pickstate;
     private Timestamp buildtime;
     private Integer attachbilltimes;
     private String remark;


    // Constructors

    /** default constructor */
    public Workbill() {
    }

	/** minimal constructor */
    public Workbill(Timestamp buildtime) {
        this.buildtime = buildtime;
    }
    
    /** full constructor */
    public Workbill(Noticebill noticebill, Staff staff, String type, String pickstate, Timestamp buildtime, Integer attachbilltimes, String remark) {
        this.noticebill = noticebill;
        this.staff = staff;
        this.type = type;
        this.pickstate = pickstate;
        this.buildtime = buildtime;
        this.attachbilltimes = attachbilltimes;
        this.remark = remark;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public Noticebill getNoticebill() {
        return this.noticebill;
    }
    
    public void setNoticebill(Noticebill noticebill) {
        this.noticebill = noticebill;
    }

    public Staff getStaff() {
        return this.staff;
    }
    
    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public String getPickstate() {
        return this.pickstate;
    }
    
    public void setPickstate(String pickstate) {
        this.pickstate = pickstate;
    }

    public Timestamp getBuildtime() {
        return this.buildtime;
    }
    
    public void setBuildtime(Timestamp buildtime) {
        this.buildtime = buildtime;
    }

    public Integer getAttachbilltimes() {
        return this.attachbilltimes;
    }
    
    public void setAttachbilltimes(Integer attachbilltimes) {
        this.attachbilltimes = attachbilltimes;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   








}