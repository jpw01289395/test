<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>�ɼ��б�</title>
	<link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../easyui/css/demo.css">
	<script type="text/javascript" src="../easyui/jquery.min.js"></script>
	<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../easyui/js/validateExtends.js"></script>
	<script type="text/javascript">
	var gradeList = ${gradeListJson};
	$(function() {
		var table;

		//datagrid��ʼ��
	    $('#dataList').datagrid({
	        title:'�ɼ��б�',
	        iconCls:'icon-more',//ͼ��
	        border: true,
	        collapsible:false,//�Ƿ���۵���
	        fit: true,//�Զ���С
	        method: "post",
	        url:"get_list?t="+new Date().getTime(),
	        idField:'id',
	        singleSelect:false,//�Ƿ�ѡ
	        pagination:true,//��ҳ�ؼ�
	        rownumbers:true,//�к�
	        sortName:'id',
	        sortOrder:'DESC',
	        remoteSort: false,
	        columns: [[
				{field:'chk',checkbox: true,width:50},
 		        {field:'id',title:'ID',width:50, sortable: true},
 		        {field:'success',title:'�ɼ�',width:150, sortable: true},
 		       {field:'cname',title:'�γ���',width:150, sortable: true,
 		        	formatter:function(value,index,row){
 		        		for(var i=0;i<courseList.length;i++){
 		        			if(courseList[i].cid == value){
 		        				return courseList[i].cname;
 		        			}
 		        		}
 		        		return value;
 		    	   }
 		        },
 		        {field:'name',title:'ѧ����',width:300, sortable: true,
 		            formatter:function(value,index,row){
                        for(var i=0;i<studentList.length;i++){
                     		if(studentList[i].sn == value){
                     		    return studentList[i].name;
                   			}
   		        		}
                        return value;
       	    	   }
 		        },
	 		]],
	        toolbar: "#toolbar"
	    });
	    //���÷�ҳ�ؼ�
	    var p = $('#dataList').datagrid('getPager');
	    $(p).pagination({
	        pageSize: 10,//ÿҳ��ʾ�ļ�¼������Ĭ��Ϊ10
	        pageList: [10,20,30,50,100],//��������ÿҳ��¼�������б�
	        beforePageText: '��',//ҳ���ı���ǰ��ʾ�ĺ���
	        afterPageText: 'ҳ    �� {pages} ҳ',
	        displayMsg: '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼',
	    });
	    //���ù����ఴť
	    $("#add").click(function(){
	    	table = $("#addTable");
	    	$("#addDialog").dialog("open");
	    });
	    //�޸�
	    $("#edit").click(function(){
	    	table = $("#editTable");
	    	var selectRows = $("#dataList").datagrid("getSelections");
        	if(selectRows.length != 1){
            	$.messager.alert("��Ϣ����", "��ѡ��һ�����ݽ��в���!", "warning");
            } else{
		    	$("#editDialog").dialog("open");
            }
	    });
	    //ɾ��
	    $("#delete").click(function(){
	    	var selectRows = $("#dataList").datagrid("getSelections");
        	var selectLength = selectRows.length;
        	if(selectLength == 0){
            	$.messager.alert("��Ϣ����", "��ѡ�����ݽ���ɾ��!", "warning");
            } else{
            	var ids = [];
            	$(selectRows).each(function(i, row){
            		ids[i] = row.id;
            	});
            	$.messager.confirm("��Ϣ����", "����༶�´���ѧ����Ϣ���޷�ɾ��������ɾ���༶������ѧ����Ϣ��", function(r){
            		if(r){
            			$.ajax({
							type: "post",
							url: "delete",
							data: {ids: ids},
							dataType:'json',
							success: function(data){
								if(data.type == "success"){
									$.messager.alert("��Ϣ����","ɾ���ɹ�!","info");
									//ˢ�±��
									$("#dataList").datagrid("reload");
									$("#dataList").datagrid("uncheckAll");
								} else{
									$.messager.alert("��Ϣ����",data.msg,"warning");
									return;
								}
							}
						});
            		}
            	});
            }
	    });

	  	//������Ӵ���
	    $("#addDialog").dialog({
	    	title: "��ӳɼ�",
	    	width: 450,
	    	height: 400,
	    	iconCls: "icon-add",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	    		{
					text:'���',
					plain: true,
					iconCls:'icon-user_add',
					handler:function(){
						var validate = $("#addForm").form("validate");
						if(!validate){
							$.messager.alert("��Ϣ����","���������������!","warning");
							return;
						} else{
							var data = $("#addForm").serialize();
							$.ajax({
								type: "post",
								url: "add",
								data: data,
								dataType:'json',
								success: function(data){
									if(data.type == "success"){
										$.messager.alert("��Ϣ����","��ӳɹ�!","info");
										//�رմ���
										$("#addDialog").dialog("close");
										//���ԭ�������
										$("#add_name").textbox('setValue', "");
										$("#add_remark").textbox('setValue', "");
										//����ˢ��ҳ������
							  			$('#dataList').datagrid("reload");

									} else{
										$.messager.alert("��Ϣ����",data.msg,"warning");
										return;
									}
								}
							});
						}
					}
				},
			],
			onClose: function(){
				$("#add_cid").textbox('setValue', "");
				$("#add_sid").textbox('setValue', "");
				$("#add_success").textbox('setValue', "");

			}
	    });

	  	//�༭�ɼ���Ϣ
	  	$("#editDialog").dialog({
	  		title: "�޸ĳɼ���Ϣ",
	    	width: 450,
	    	height: 400,
	    	iconCls: "icon-edit",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	    		{
					text:'�ύ',
					plain: true,
					iconCls:'icon-edit',
					handler:function(){
						var validate = $("#editForm").form("validate");
						if(!validate){
							$.messager.alert("��Ϣ����","���������������!","warning");
							return;
						} else{

							var data = $("#editForm").serialize();

							$.ajax({
								type: "post",
								url: "edit",
								data: data,
								dataType:'json',
								success: function(data){
									if(data.type == "success"){
										$.messager.alert("��Ϣ����","�޸ĳɹ�!","info");
										//�رմ���
										$("#editDialog").dialog("close");

										//����ˢ��ҳ������
							  			$('#dataList').datagrid("reload");
							  			$('#dataList').datagrid("uncheckAll");

									} else{
										$.messager.alert("��Ϣ����",data.msg,"warning");
										return;
									}
								}
							});
						}
					}
				},
			],
			onBeforeOpen: function(){
				var selectRow = $("#dataList").datagrid("getSelected");
				//����ֵ
				$("#edit-id").val(selectRow.id);
				$("#edit_sid").textbox('setValue', selectRow.sid);
				$("#edit_cid").combobox('setValue', selectRow.cid);
				$("#edit_success").textbox('setValue', selectRow.success);
			}
	    });


	  	//������ť
	  	$("#search-btn").click(function(){
	  		$('#dataList').datagrid('reload',{
	  			name:$("#search-name").textbox('getValue'),
	  			gradeId:$("#search-grade-id").combobox('getValue')
	  		});
	  	});
	});
	</script>
</head>
<body>
	<!-- �����б� -->
	<table id="dataList" cellspacing="0" cellpadding="0">

	</table>
	<!-- ������ -->
	<div id="toolbar">
		<c:if test="${userType == 1}">
		<div style="float: left;"><a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">���</a></div>
			<div style="float: left;" class="datagrid-btn-separator"></div>
		<div style="float: left;"><a id="edit" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">�޸�</a></div>
			<div style="float: left;" class="datagrid-btn-separator"></div>
		</c:if>
		<div>
			<c:if test="${userType == 1}">
			<a id="delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-some-delete',plain:true">ɾ��</a>
			</c:if>
			ѧ������<input id="search-name" class="easyui-textbox" />
			�����γ̣�
			<select id="search-grade-id" class="easyui-combobox" style="width: 150px;">
				<option value="">ȫ��</option>
				<c:forEach items="${ courseList}" var="course">
	    			<option value="${course.cid }">${course.cname }</option>
	    		</c:forEach>
			</select>
			<a id="search-btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">����</a>
		</div>
	</div>

	<!-- ��Ӵ��� -->
	<div id="addDialog" style="padding: 10px;">
   		<form id="addForm" method="post">
	    	<table id="addTable" cellpadding="8">
	    		<tr >
	    			<td>�༶��:</td>
	    			<td>
	    				<input id="add_name"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="name" data-options="required:true, missingMessage:'����д�༶��'"  />
	    			</td>
	    		</tr>
	    		<tr >
	    			<td>�����꼶:</td>
	    			<td>
	    				<select id="add_gradeId"  class="easyui-combobox" style="width: 200px;" name="gradeId" data-options="required:true, missingMessage:'��ѡ�������꼶'">
	    					<c:forEach items="${ gradeList}" var="grade">
	    						<option value="${grade.id }">${grade.name }</option>
	    					</c:forEach>
	    				</select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>��ע:</td>
	    			<td><input id="add_remark" style="width: 256px; height: 180px;" class="easyui-textbox" type="text" name="remark" data-options="multiline:true"  /></td>
	    		</tr>
	    	</table>
	    </form>
	</div>


	<!-- �޸Ĵ��� -->
	<div id="editDialog" style="padding: 10px">
    	<form id="editForm" method="post">
    		<input type="hidden" name="id" id="edit-id">
	    	<table id="editTable" border=0 cellpadding="8" >
	    		<tr >
	    			<td>�༶��:</td>
	    			<td>
	    				<input id="edit_name"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="name" data-options="required:true, missingMessage:'����д�༶��'"  />
	    			</td>
	    		</tr>
	    		<tr >
	    			<td>�����꼶:</td>
	    			<td>
	    				<select id="edit_gradeId"  class="easyui-combobox" style="width: 200px;" name="gradeId" data-options="required:true, missingMessage:'��ѡ�������꼶'">
	    					<c:forEach items="${ gradeList}" var="grade">
	    						<option value="${grade.id }">${grade.name }</option>
	    					</c:forEach>
	    				</select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>��ע:</td>
	    			<td><input id="edit_remark" style="width: 256px; height: 180px;" class="easyui-textbox" type="text" name="remark" data-options="multiline:true"  /></td>
	    		</tr>
	    	</table>
	    </form>
	</div>


</body>
</html>