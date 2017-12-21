package cn.itcast.invoice.invoice.supplier.web;

import java.util.List;

import cn.itcast.invoice.invoice.supplier.business.ebi.SupplierEbi;
import cn.itcast.invoice.invoice.supplier.vo.SupplierModel;
import cn.itcast.invoice.invoice.supplier.vo.SupplierQueryModel;
import cn.itcast.invoice.util.base.BaseAction;

public class SupplierAction extends BaseAction{
	public SupplierModel sm = new SupplierModel();
	public SupplierQueryModel sqm = new SupplierQueryModel();

	private SupplierEbi supplierEbi;
	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	//跳转到列表页面
	public String list(){
		setDataTotal(supplierEbi.getCount(sqm));
		List<SupplierModel> supplierList = supplierEbi.getAll(sqm,pageNum,pageCount);
		put("supplierList",supplierList);
		return LIST;
	}

	//保存/修改
	public String save(){
		if(sm.getUuid()== null){
			supplierEbi.save(sm);
		}else{
			supplierEbi.update(sm);
		}
		return TO_LIST;
	}

	//跳转到添加/修改页面
	public String input(){
		if(sm.getUuid()!=null){
			sm = supplierEbi.get(sm.getUuid());
		}
		return INPUT;
	}

	//删除
	public String delete(){
		supplierEbi.delete(sm);
		return TO_LIST;
	}

}
