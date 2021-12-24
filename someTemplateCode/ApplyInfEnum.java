package com.msyd.manage.modules.order.entity.tm;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *  进件信息
 */
public enum ApplyInfEnum {

	// 还有数据类型和长度

	//		借款人基本信息。以json格式展示，复杂对象	
    OPENID("openId","","true",""),
    OUTTRADENO("outTradeNo","","true",""),	// 外部订单号
	MERCHANTNO("merchantNo","","true",""), // 商户号
	STORENO("storeNo","","true",""),	// 门店号

    CUSTBASEINFO_apptTyp("apptTyp","","true","custBaseInfo"),		// 申请人类型（必须为01，表示主申请人）
    CUSTBASEINFO_custName("custName","","true","custBaseInfo"),		// 客户姓名
	CUSTBASEINFO_idTyp("idTyp","","true","custBaseInfo"),		// 证件类型（22: 护照;00: 员工代码; 20: 身份证;23: 军官证; 25: 港澳居民来往内地通行证; 26: 台湾居民来往大陆通行证; 2X: 其他证件;30: 组织机构代码证;31: 营业执照） 
	CUSTBASEINFO_idNo("idNo","","true","custBaseInfo"),		// 证件号码
	CUSTBASEINFO_certValidDate("certValidDate","","true","custBaseInfo"),		// 证件有效期（yyyy-mm-dd）
	CUSTBASEINFO_certRegiAddr("certRegiAddr","","true","custBaseInfo"),		// 证件登记地址
	CUSTBASEINFO_certSignOrg("certSignOrg","","true","custBaseInfo"),		// 发证机关
	CUSTBASEINFO_nation("nation","","true","custBaseInfo"),		// 民族（01: 汉族; 02: 蒙古族; 03: 回族; 04: 藏族; 05: 维吾尔族; 06: 苗族; 07: 彝族; 08: 壮族; 09: 布依族; 10: 朝鲜族; 11: 满族; 12: 侗族; 13: 瑶族; 14: 白族; 15: 土家族; 16: 哈尼族; 17: 哈萨克族; 18: 傣族; 19: 黎族; 20: 傈僳族; 21: 佤族; 22: 畲族; 23: 高山族; 24: 拉祜族; 25: 水族; 26: 东乡族; 27: 纳西族; 28: 景颇族; 29: 柯尔克孜族; 30: 土族; 31: 达斡尔族; 32: 仫佬族; 33: 羌族; 34: 布朗族; 35: 撒拉族; 36: 毛难族; 37: 仡佬族; 38: 锡伯族; 39: 阿昌族; 40: 普米族; 41: 塔吉克族; 42: 怒族; 43: 乌孜别克族; 44: 俄罗斯族; 45: 鄂温克族; 46: 崩龙族; 47: 保安族; 48: 裕固族; 49: 京族; 50: 塔塔尔族; 51: 独龙族; 52: 鄂伦春族; 53: 赫哲族; 54: 门巴族; 55: 珞巴族; 56: 基诺族） 
	CUSTBASEINFO_sex("sex","","true","custBaseInfo"),		// 性别（0:女; 1:男）
	CUSTBASEINFO_marriage("marriage","","false","custBaseInfo"),		// 婚姻状况 1:未婚 2:离异 3:丧偶 B:已婚无子女 A:已婚有子女 9:其他 
	CUSTBASEINFO_indivEdu("indivEdu","","false","custBaseInfo"),		// 最高学历（00: 硕士及以上; 10: 本科;20: 大专; 30: 高中; 40: 初中及以下）
	CUSTBASEINFO_indivDegree("indivDegree","","false","custBaseInfo"),		// 学位（0: 其他; 1: 名誉博士; 2: 博士; 3: 硕士; 4: 学士; 9: 未知） 
	CUSTBASEINFO_indivIndtryPaper("indivIndtryPaper","","true","custBaseInfo"),		// 行业（A: 农、林、牧、渔业; B: 采掘业; C: 制造业; D: 电力、燃气及水的生产和供应业; E: 建筑业; F: 交通运输、仓储和邮政业; G: 信息传输、计算机服务和软件业; H: 批发和零售业; I: 住宿和餐饮业; J: 金融业; K: 房地产业; L: 租赁和商务服务业; M: 科学研究、技术服务业和地质勘察业; N: 水利、环境和公共设施管理业; O: 居民服务和其他服务业; P: 教育; Q: 卫生、社会保障和社会福利业; R: 文化、体育和娱乐业; S: 公共管理和社会组织; T: 国际组织; Z: 未知） 
	CUSTBASEINFO_indivPro("indivPro","","true","custBaseInfo"),		// 职业（0: 国家机关、党群组织、企业、事业单位负责人; 1: 专业技术人员; 3: 办事人员和有关人员; 4: 商业、服务业人员; 5: 农、林、牧、渔、水利业生产人员; 6: 生产、运输设备操作人员及有关人员; X: 军人; Y: 不便分类的其他从业人员; Z: 未知） 
	CUSTBASEINFO_indivProfsn("indivProfsn","","false","custBaseInfo"),		// 职称（00: 无; 10: 高级; 20: 中级; 30: 初级; 99: 未知） 
	CUSTBASEINFO_regProvince("regProvince","","true","custBaseInfo"),		// 户籍所在省
	CUSTBASEINFO_regCity("regCity","","true","custBaseInfo"),		// 户籍所在市
	CUSTBASEINFO_liveInfo("liveInfo","","true","custBaseInfo"),		// 现住房情况（10: 自购现无贷款; 20: 自购现有贷款; 30: 与父母同住; 40: 宅基地房; 50: 租房; 60: 军产房; 70: 小产权; 80: 单位宿舍; 99: 其他） 
	CUSTBASEINFO_liveMj("liveMj","","false","custBaseInfo"),		// 现住房面积
	CUSTBASEINFO_liveProvince("liveProvince","","true","custBaseInfo"),		// 现住房所在省
	CUSTBASEINFO_liveCity("liveCity","","true","custBaseInfo"),		// 现住房所在市
	CUSTBASEINFO_liveArea("liveArea","","true","custBaseInfo"),		// 现住房所在区
	CUSTBASEINFO_liveAddr("liveAddr","","true","custBaseInfo"),		// 现住房地址
	CUSTBASEINFO_pptyLive("pptyLive","","true","custBaseInfo"),		// 自有房情况（N: 其他; NO: 无; Y: 同现住房地址）
		//   自有房与现住房相同(PPTY_LIVE为Y)则无需报送，value为""
	CUSTBASEINFO_pptyProvince("pptyProvince","","C","custBaseInfo"),		// 自有房所在省
	CUSTBASEINFO_pptyCity("pptyCity","","C","custBaseInfo"),		// 自有房所在市
	CUSTBASEINFO_pptyArea("pptyArea","","C","custBaseInfo"),		// 自有房所在区
	CUSTBASEINFO_pptyAddr("pptyAddr","","C","custBaseInfo"),		// 自有房地址
	CUSTBASEINFO_indivFmlyZone("indivFmlyZone","","false","custBaseInfo"),		// 家庭电话区号
	CUSTBASEINFO_indivFmlyTel("indivFmlyTel","","false","custBaseInfo"),		// 家庭电话
	CUSTBASEINFO_mobilePhone("mobilePhone","","true","custBaseInfo"),		// 移动电话
	CUSTBASEINFO_isGuarCust("isGuarCust","","false","custBaseInfo"),		// 是否抵押人（Y: 是; N: 否）
	CUSTBASEINFO_licInd("licInd","","false","custBaseInfo"),		// 本人是否有驾照（Y: 是; N: 否）
	
	// 	借款人工作信息。以json格式展示，复杂对象custWorkInfo
	CUSTWORKINFO_positionOpt("positionOpt","","true","custWorkInfo"),				// 工作性质
	CUSTWORKINFO_indivEmpName("indivEmpName","","C","custWorkInfo"),	// 现单位名称
	CUSTWORKINFO_indivBranch("indivBranch","","false","custWorkInfo"),	// 所在部门
	CUSTWORKINFO_indivMthInc("indivMthInc","","true","custWorkInfo"),	// 月均收入
	CUSTWORKINFO_indivEmpTyp("indivEmpTyp","","false","custWorkInfo"),	// 现单位性质
	CUSTWORKINFO_indivEmpYrs("indivEmpYrs","","false","custWorkInfo"),	// 现单位工龄
	CUSTWORKINFO_indivEmpProvince("indivEmpProvince","","false","custWorkInfo"),	// 现单位所在省
	CUSTWORKINFO_indivEmpCity("indivEmpCity","","false","custWorkInfo"),	// 现单位所在市
	CUSTWORKINFO_indivEmpArea("indivEmpArea","","false","custWorkInfo"),	// 现单位所在区
	CUSTWORKINFO_indivEmpAddr("indivEmpAddr","","false","custWorkInfo"),	// 现单位地址
	CUSTWORKINFO_indivEmpZone("indivEmpZone","","false","custWorkInfo"),	// 办公电话区号
	CUSTWORKINFO_indivEmpTel("indivEmpTel","","false","custWorkInfo"),	// 办公电话
	CUSTWORKINFO_indivEmpTelSub("indivEmpTelSub","","false","custWorkInfo"),	// 办公电话分机
	CUSTWORKINFO_positionRmk("positionRmk","","C","custWorkInfo"),	// 收入来源
	
	// 	配偶基本信息，以json格式展示，复杂对象spouseBaseInfo
	SPOUSEBASEINFO_spouseName("spouseName","","false","spouseBaseInfo"),  //配偶姓名			
	SPOUSEBASEINFO_spouseIdTyp("spouseIdTyp","","false","spouseBaseInfo"),  //配偶证件类型
	SPOUSEBASEINFO_spouseIdNo("spouseIdNo","","false","spouseBaseInfo"),  //配偶证件号码
	SPOUSEBASEINFO_spouseMobile("spouseMobile","","false","spouseBaseInfo"),  //配偶移动电话

	// 	配偶工作信息，以json格式展示，复杂对象spouseWorkInfo
	SPOUSEWORKINFO_spouseEmp("spouseEmp","","false","spouseWorkInfo"),	 //单位名称			
	SPOUSEWORKINFO_spouseBranch("spouseBranch","","false","spouseWorkInfo"),	 //所在部门
	SPOUSEWORKINFO_spMthInc("spMthInc","","false","spouseWorkInfo"),	 //月均收入
	SPOUSEWORKINFO_spouseEmpProvince("spouseEmpProvince","","false","spouseWorkInfo"),	 //现单位所在省
	SPOUSEWORKINFO_spouseEmpCity("spouseEmpCity","","false","spouseWorkInfo"),	 //现单位所在市
	SPOUSEWORKINFO_spouseEmpArea("spouseEmpArea","","false","spouseWorkInfo"),	 //现单位所在区
	SPOUSEWORKINFO_spouseEmpAddr("spouseEmpAddr","","false","spouseWorkInfo"),	 //现单位地址
	SPOUSEWORKINFO_spouseEmpZone("spouseEmpZone","","false","spouseWorkInfo"),	 //配偶办公电话区号
	SPOUSEWORKINFO_spouseEmpTel("spouseEmpTel","","false","spouseWorkInfo"),	 //配偶办公电话
	SPOUSEWORKINFO_spouseEmpTelSub("spouseEmpTelSub","","false","spouseWorkInfo"),	 //配偶办公电话分机
	
	// 	紧急联系人，以json格式展示，复杂对象emergencyContract
	EMERGENCYCONTRACT_relName("relName","","false","emergencyContractList"),		// 紧急联系人姓名
	EMERGENCYCONTRACT_idTyp("idTyp","","false","emergencyContractList"),		// 证件类型
	EMERGENCYCONTRACT_idNo("idNo","","false","emergencyContractList"),		// 证件号码
	EMERGENCYCONTRACT_relRelation("relRelation","","false","emergencyContractList"),		// 与申请人关系（01: 父母或子女; 02: 兄弟姐妹; 03: 同事; 04: 同学; 05: 朋友; 06: 夫妻; 07: 本人; 99: 其他）
	EMERGENCYCONTRACT_relMobile("relMobile","","false","emergencyContractList"),		// 联系电话
	EMERGENCYCONTRACT_relEmpName("relEmpName","","false","emergencyContractList"),		// 单位名称
	EMERGENCYCONTRACT_linkmanPro("linkmanPro","","false","emergencyContractList"),		// 居住地所在省
	EMERGENCYCONTRACT_linkmanCity("linkmanCity","","false","emergencyContractList"),		// 居住地所在市
	EMERGENCYCONTRACT_linkmanArea("linkmanArea","","false","emergencyContractList"),		// 居住地所在区
	EMERGENCYCONTRACT_linkmanDetail("linkmanDetail","","false","emergencyContractList"),		// 居住地地址
	

		// 	贷款信息，以json格式展示，复杂对象loanInfo
	LOANINFO_platApplCde("platApplCde","","true","loanInfo"),		// 平台业务编号
	LOANINFO_carDealer("carDealer","","true","loanInfo"),		// 车商名称
	LOANINFO_carShopCity("carShopCity","","true","loanInfo"),		// 车商门店所在城市
	LOANINFO_saler("saler","","true","loanInfo"),		// 销售人员
	LOANINFO_salerMobile("salerMobile","","true","loanInfo"),		// 销售人员手机号
	LOANINFO_loanTyp("loanTyp","","true","loanInfo"),		// 贷款产品
	LOANINFO_loanProm("loanProm","","true","loanInfo"),		// 营销专案
	LOANINFO_mtdCde("mtdCde","","true","loanInfo"),		// 还款方式（RP01: 等额本息; RP02: 等额本金）
	LOANINFO_appryTnr("appryTnr","","true","loanInfo"),		// 申请期限
	LOANINFO_proPurAmt("proPurAmt","","true","loanInfo"),		// 车辆购买价/车辆成交价
	LOANINFO_fstPct("fstPct","","true","loanInfo"),		// 车价首付比例
	LOANINFO_carApplyAmt("carApplyAmt","","true","loanInfo"),		// 车贷申请金额
	LOANINFO_licCity("licCity","","true","loanInfo"),		// 上牌城市
	LOANINFO_applServiceFlag("applServiceFlag","","true","loanInfo"),		// 营运标识（Y: 是; N: 否）
	LOANINFO_guarCustType("guarCustType","","true","loanInfo"),		// 抵押人类型（01: 主申人; 02: 共申人; 03: 借标人; 04: 公户）
	LOANINFO_guarCompName("guarCompName","","false","loanInfo"),		// 公户名称
	LOANINFO_guarCompProv("guarCompProv","","false","loanInfo"),		// 公户联系地址省编码
	LOANINFO_guarCompCity("guarCompCity","","false","loanInfo"),		// 公户联系地址市编码
	LOANINFO_guarCompArea("guarCompArea","","false","loanInfo"),		// 公户联系地址区编码
	LOANINFO_guarCompAddr("guarCompAddr","","false","loanInfo"),		// 公户联系地址详细地址
	LOANINFO_guarCompTel("guarCompTel","","false","loanInfo"),		// 公户联系电话
	LOANINFO_isCprsvLoan("isCprsvLoan","","true","loanInfo"),		// 是否综合贷（Y: 是; N: 否）

	PROJECTInf("projectList","","C","projectList"),		// 是否综合贷（Y: 是; N: 否）
	PROJECTINf_proTyp("proTyp","","C","projectList"),		// 费用类型（01: 保险; 02: 购置税; 04: 其他费用）
	PROJECTINf_projWholeAmt("projWholeAmt","","C","projectList"),		// 费用对应项原始金额
	
	
	// 	车辆信息，以json格式展示，复杂对象carInfo
	CARINFO_carModelNam("carModelNam","","true","carInfo"),		//车辆类型名称
	CARINFO_carBrandNam("carBrandNam","","true","carInfo"),	//车型品牌名称
	CARINFO_carClassNam("carClassNam","","true","carInfo"),	//车型车系名称
	CARINFO_carModelCde("carModelCde","","false","carInfo"),	//车型名称

		// 	二手车信息，以json格式展示，复杂对象secondHandCarInfo
	SECONDHANDCARINFO_sellerName("sellerName","","false","secondHandCarInfo"),		// 卖家姓名
	SECONDHANDCARINFO_sellerCardType("sellerCardType","","false","secondHandCarInfo"),		// 卖家证件类型
	SECONDHANDCARINFO_sellerIdNo("sellerIdNo","","false","secondHandCarInfo"),		// 卖家证件号码
	SECONDHANDCARINFO_sellerAddrProvince("sellerAddrProvince","","false","secondHandCarInfo"),		// 行驶证地址-省
	SECONDHANDCARINFO_sellerAddrCity("sellerAddrCity","","false","secondHandCarInfo"),		// 行驶证地址-市
	SECONDHANDCARINFO_sellerAddrArea("sellerAddrArea","","false","secondHandCarInfo"),		// 行驶证地址-区
	SECONDHANDCARINFO_sellerAddr("sellerAddr","","false","secondHandCarInfo"),		// 行驶证详细地址
	SECONDHANDCARINFO_scarDriDist("scarDriDist","","C","secondHandCarInfo"),		// 行驶里程(km)
	SECONDHANDCARINFO_mileAgeType("mileAgeType","","C","secondHandCarInfo"),		// 二手车公里数类型
	SECONDHANDCARINFO_color("color","","C","secondHandCarInfo"),		// 颜色
	SECONDHANDCARINFO_scarCol("scarCol","","false","secondHandCarInfo"),		// 登记证车身颜色
	SECONDHANDCARINFO_scarLicNo("scarLicNo","","false","secondHandCarInfo"),		// 车牌号
	SECONDHANDCARINFO_interior("interior","","false","secondHandCarInfo"),		// 内饰情况
	SECONDHANDCARINFO_motorId("motorId","","false","secondHandCarInfo"),		// 发动机号
	SECONDHANDCARINFO_leaveRegisterDate("leaveRegisterDate","","C","secondHandCarInfo"),		// 出厂日期
	SECONDHANDCARINFO_zone("zone","","C","secondHandCarInfo"),		// 交易所在市
	SECONDHANDCARINFO_firstRegisterDate("firstRegisterDate","","C","secondHandCarInfo"),		// 二手车上牌时间
	SECONDHANDCARINFO_surface("surface","","false","secondHandCarInfo"),		// 漆面状况
	SECONDHANDCARINFO_vehicleIdNum("vehicleIdNum","","C","secondHandCarInfo"),		// 车架号(VIN)
	SECONDHANDCARINFO_workState("workState","","C","secondHandCarInfo"),		// 车辆技术状况
	SECONDHANDCARINFO_transferTimes("transferTimes","","C","secondHandCarInfo"),		// 交易次数

	EVALUATEPRICE("evaluatePrice","","true",""),			// NUMBER	16,2	是	车辆认证价
	POWERSOURCE("powerSource","","true",""),			// String	10	是	能源类型（汽油、柴油、 油电混合、纯电动）
	TRADETYPE("tradeType","","true",""),			// String	2	是	交易类型（新车01，二手车02）
	NETCHECKPRICE("netCheckPrice","","C","");			// NUMBER	16,2	特定条件下必填	网查价 ;


    private String key;
    private Object val;
    private String requied;  // 是否 必填 C表示特定条件下必填 （）
    private String belongObj;
//    private String colType;
    
//    private static final Map<String, ApplyInfEnum> aplyInfMap;

    ApplyInfEnum(String key, Object val, String requied, String belongObj){
        this.key=key;
        this.val=val;
        this.requied=requied;
        this.belongObj=belongObj;
    }

    ApplyInfEnum() {

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getVal() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }

    public String getRequied() {
        return requied;
    }

    public void setRequied(String requied) {
        this.requied = requied;
    }

	public String getBelongObj() {
		return belongObj;
	}

	public void setBelongObj(String belongObj) {
		this.belongObj = belongObj;
	}

	public static Map<String, ApplyInfEnum> getInfMap() {
         Map<String, ApplyInfEnum> aplyInfMap=null;
        if (aplyInfMap == null) {
            aplyInfMap = new HashMap<String, ApplyInfEnum>((int) ((float) values().length / 0.75F) + 1);
            ApplyInfEnum[] arr$ = values(); //  枚举类型的所有常量可以通过调用该类型的隐式public static T[] values()方法来获得。
            int len$ = arr$.length;
            for (int i$ = 0; i$ < len$; ++i$) {
                ApplyInfEnum item = arr$[i$];
                String itmkey=item.getKey();
                if(aplyInfMap.containsKey(itmkey)){
					itmkey=item.getBelongObj()+"#"+item.getKey();
				}
                aplyInfMap.put(itmkey, item);
            }
        }
        return aplyInfMap;
    }

//    public String getColType() {
//        return colType;
//    }
//
//    public void setColType(String colType) {
//        this.colType = colType;
//    }

    public static  ApplyInfEnum getByKey(String key, Class<ApplyInfEnum> enumClass) {
        //通过反射取出Enum所有常量的属性值
        for (ApplyInfEnum each: enumClass.getEnumConstants()) {
            //利用code进行循环比较，获取对应的枚举
            if (key.equals(each.getKey())) {
                return each;
            }
        }
        return null;
    }


	public static  ApplyInfEnum getByKey(String key, String belongObj) {
		Class<ApplyInfEnum> enumClass=ApplyInfEnum.class;
    	//通过反射取出Enum所有常量的属性值
		for (ApplyInfEnum each: enumClass.getEnumConstants()) {
			//利用code进行循环比较，获取对应的枚举
			if (key.equals(each.getKey()) && each.getBelongObj().equals(belongObj)) {
				return each;
			}
		}
		return null;
	}

	/**
	 * 直接获得 从map 对象中取值的键
	 *
 	 */
	public static  String  getParamKey(String key) {
		//通过反射取出Enum所有常量的属性值
		Class<ApplyInfEnum> enumClass=ApplyInfEnum.class;
		for (ApplyInfEnum each: enumClass.getEnumConstants()) {
			//利用code进行循环比较，获取对应的枚举
			if (key.equals(each.getKey())) {
				return each.getBelongObj()+"#"+each.getKey();
			}
		}
		return null;
	}

	public static  String  getParamKeySplitObj(String key,String belongObj) {
		//通过反射取出Enum所有常量的属性值
		Class<ApplyInfEnum> enumClass=ApplyInfEnum.class;
		for (ApplyInfEnum each: enumClass.getEnumConstants()) {
			//利用code进行循环比较，获取对应的枚举
			if (key.equals(each.getKey()) && each.getBelongObj().equals(belongObj)) {
				return each.getBelongObj()+"#"+each.getKey();
			}
		}
		return null;
	}

//
//    static {
//        aplyInfMap = new HashMap<String, ApplyInfEnum>((int) ((float) values().length / 0.75F) + 1);
//        ApplyInfEnum[] arr$ = values(); //  枚举类型的所有常量可以通过调用该类型的隐式public static T[] values()方法来获得。
//		int len$ = arr$.length;
//        for (int i$ = 0; i$ < len$; ++i$) {
//            ApplyInfEnum item = arr$[i$];
//			aplyInfMap.put(item.key, item);
//        }
//    }

}
