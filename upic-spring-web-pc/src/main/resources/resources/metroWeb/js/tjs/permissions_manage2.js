var data2Url = "/operator/searchRole";
var changeRoleInfoUrl = "/operator/updateMyRole";
var deleteRoleUrl = "";
var updateRoleResourceRelationUrl = "/operator/updateRoleResourceRelation"
var pageSize2 = 0;
var totalPages2 = -1;
var pageNum2 = 0;
var requestData2 = {};
var zNodes = [];
var searchResource = "";
var getAllResourceUrl = "/operator/listResource";
var getResourceByRoleIdUrl = "/operator/listResourceByRoleId"
function getJueSe() {
    pageSize2 = $("#select-small2").children('option:selected').text();
    getData2(pageNum2, data2Url);
}

// ajax获取页面内容
function getData2(pageNum2, data2Url, pageSize2) {
    requestData2.size = parseInt($("#select-small2")
        .children('option:selected').text());
    requestData2.page = pageNum2;
    $.ajax({
        type: "GET",
        url: data2Url,
        data: requestData2,
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (result) {
            if (result != "" && result != null) {
                addHtmls2(result, pageNum2, requestData2);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}

function addHtmls2(datas) {
    totalPages2 = datas.totalElements;
    var data = datas.content;
    var htmls = "";
    for (var i = 0; i < data.length; i++) {
        htmls += "<tr><td><input type='checkbox' class='checkboxes' value='1' id='"
            + data[i].userNum + "'/></td>";
        htmls += "<td class='center_td'>"
            + (parseInt(pageNum2) * parseInt(pageSize2) + i + 1) + "</td>";
        htmls += "<td>" + data[i].roleName + "</td>";
        if (data[i].type === "SUPER_ADMINISTRATOR") {
            htmls += "<td>超级管理员</td>";
        } else {
            htmls += "<td>普通管理员</td>"
        }
        if (data[i].status === "NORMAL_CONDITION") {
            htmls += "<td>正常</td>";
        } else {
            htmls += "<td>冻结</td>";
        }
        htmls += "<td>" + getDate(data[i].creatTime, 'yyyy-MM-dd') + "</td>";
        htmls += "<td>";
        htmls += "<div class='message_div'><a href='#mymodal10' data-toggle='modal'><span onclick=fenPeiQuanXian('"
            + data[i].id + "','" + (i + 1) + "')>【分配权限】</span></a>";
        htmls += "<span class='space'>|</span>";
        htmls += "<a href='#mymodal7' data-toggle='modal'><span onclick=changeMyRole('" + data[i].id + "','" + data[i].roleName + "','" + data[i].type + "','" + data[i].content + "')>【修改】</span></a>";
        htmls += "<span class='space'>|</span>";
        htmls += "<a href='#mymodal8' data-toggle='modal'><span onclick=deleteA("
            + data[i].userNum
            + ","
            + data[i].username
            + ")>【删除】</span></a>";
        htmls += "</div></td>";
        htmls += "</tr>";
    }
    $("#data2").html(htmls);
    page2(datas, data2Url, datas.size, datas.number);
}

function changeMyRole(roleId, roleName, type, content) {
    var htmls = "";
    htmls += "<div class='modal-header'>";
    htmls += "<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button>";
    htmls += "<h4>修改角色</h4>";
    htmls += "</div>";
    htmls += "<div class='modal-body'>";
    htmls += "<form action='#' class='form-horizontal'>";
    htmls += "<div class='control-group'>";
    htmls += "<label class='control-label'>角色名称：</label>";
    htmls += "<div class='controls'>";
    htmls += "<input type='text' id='" + roleId + "name' class='input-medium' value='" + roleName + "'/>";
    htmls += "</div>";
    htmls += "</div>";
    htmls += "<div class='control-group'>";
    htmls += "<label class='control-label'>角色类型：</label>";
    htmls += "<div class='controls'>";
    htmls += "<span>" + type + "</span>";
    htmls += "</div>";
    htmls += "</div>";
    htmls += "<div class='control-group'>";
    htmls += "<label class='control-label'>角色描述：</label>";
    htmls += "<div class='controls'>";
    htmls += "<textarea id='" + roleId + "content'>" + content + "</textarea>";
    htmls += "</div>";
    htmls += "</div>";
    htmls += "</form>";
    htmls += "</div>";
    htmls += "<div class='modal-footer'>";
    htmls += "<button class='btn btn-primary' data-dismiss='modal' onclick=changeRoleInfo('" + roleId + "')>确定</button>";
    htmls += "<button class='btn' data-dismiss='modal' aria-hidden='true'>取消</button>";
    htmls += "</div>";
    $("#mymodal7").html(htmls);
}

function changeRoleInfo(roleId) {
    var Data = {};
    Data.id = roleId;
    Data.roleName = $("#" + roleId + "name").val();
    Data.content = $("#" + roleId + "content").val();

    var roleInfo = "{ ";
    for (var item in Data) {
        roleInfo += "'" + item + "':'" + Data[item] + "',";
    }
    roleInfo += " }";

    $.ajax({
        type: "POST",
        url: changeRoleInfoUrl,
        data: {
            roleInfo: roleInfo
        },
        success: function (result) {
            alert("修改成功");
            getData2(pageNum2, data2Url);
        }
    });
}

function deleteA(num, name) {
    var htmls = "";
    htmls += "<div class='modal-header'>";
    htmls += "<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button>";
    htmls += "<h4>确认提示</h4>";
    htmls += "</div>";
    htmls += "<div class='modal-body'>";
    htmls += "<form action='#' class='form-horizontal'>";
    htmls += "<p>删除角色<span>【" + name + "】</span>?</p>";
    htmls += "</form>";
    htmls += "</div>";
    htmls += "<div class='modal-footer'>";
    htmls += "<button class='btn btn-primary' data-dismiss='modal' onclick=deleteRole("
        + num + ")>确定</button>";
    htmls += "<button class='btn' data-dismiss='modal' aria-hidden='true'>取消</button>";
    htmls += "</div>";
    $("#deleteRole").html(htmls);
}

function deleteRole(num) {
    var Data = {};
    Data.userNum = num;

    $.ajax({
        type: "GET",
        url: deleteRoleUrl,
        data: Data,
        success: function (result) {
            alert("删除成功")
        }
    });
}
/** *****************************************分配权限*************************************************** */
var allResource = "";
var roleResource = "";
var roleId = "";
var arryNodes = new Array();
function fenPeiQuanXian(roleIds, j) {
    getAllResource(1, roleIds, getAllResourceUrl);
    roleId = roleIds;
    getAllRoleOperator(roleIds, j);
}

function getAllRoleOperator(roleId, j) {
    $.ajax({
        type: "GET",
        url: "/operator/getAllRoleOperator",
        data: {
            roleId: roleId
        },
        success: function (result) {
            var htmls = "";
            for (var i = 0; i < result.length; i++) {
                htmls += "<tr>";
                htmls += "<td>" + j + "</td>";
                htmls += "<td>" + result[i].jobNum + "</td>";
                htmls += "<td>" + result[i].username + "</td>";
                htmls += "</tr>";
            }
            $("#getTheAllRoleOperator").html(htmls);
        }
    });
}

function getAllResource(type, roleId, url) {
    $.ajax({
        type: "GET",
        url: url,
        data: {
            roleId: roleId
        },
        success: function (result) {
            if (type == 1) {
                getAllResource(2, roleId, getResourceByRoleIdUrl);
                allResource = result;
            } else if (type == 2) {
                roleResource = result;
                cleanUpZtree();
            }
        }
    });
}

function cleanUpZtree() {
    var setting = {
        check: {
            enable: true
        },
        data: {
            key: {
                title: "title"
            },
            simpleData: {
                enable: true
            }
        },
        callback: {
            onCheck: onCheck
        }
    };
    setting.check.chkboxType = {
        "Y": "ps",
        "N": "ps"
    };
    arryNodes = new Array();
    for (var i = 0; i < allResource.length; i++) {
        var zNode = new Object();
        zNode.id = allResource[i].id;
        zNode.name = allResource[i].resourceName;
        zNode.pId = allResource[i].fatherId;
        zNode.isParent = isLeaf(allResource[i].isLeaf);

        zNode.myId = allResource[i].id;
        zNode.resourceNum = allResource[i].resourceNum;
        zNode.resourceName = allResource[i].resourceName;
        zNode.url = allResource[i].url;
        zNode.status = allResource[i].status;
        zNode.type = allResource[i].type;
        zNode.level = allResource[i].level;
        zNode.myFatherId = allResource[i].fatherId;
        for (var j = 0; j < roleResource.length; j++) {
            if (allResource[i].id == roleResource[j].id) {
                zNode.checked = true;
            }
        }
        zNode.open = true;
        arryNodes.push(zNode);
    }
    $.fn.zTree.init($("#treeDemo"), setting, arryNodes);
    $("#hideNodesBtn").bind("click", {
        type: "rename"
    }, hideNodes);
    $("#showNodesBtn").bind("click", {
        type: "icon"
    }, showNodes);
    setTitle();
    count();
}
function isLeaf(leaf) {
    return (leaf == 0 ? true : false);
}

function isParent(parent) {
    return parent ? 0 : 1;
}

function onCheck(e, treeId, treeNode) {
    count();
}

function setTitle(node) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    var nodes = node ? [node] : zTree.transformToArray(zTree.getNodes());
    for (var i = 0, l = nodes.length; i < l; i++) {
        var n = nodes[i];
        n.title = "[" + n.id + "] isFirstNode = " + n.isFirstNode
            + ", isLastNode = " + n.isLastNode;
        zTree.updateNode(n);
    }
}
function count() {
    function isForceHidden(node) {
        if (!node.parentTId)
            return false;
        var p = node.getParentNode();
        return !!p.isHidden ? true : isForceHidden(p);
    }

    var zTree = $.fn.zTree.getZTreeObj("treeDemo"), checkCount = zTree
        .getCheckedNodes(true).length, nocheckCount = zTree
        .getCheckedNodes(false).length, hiddenNodes = zTree
        .getNodesByParam("isHidden", true), hiddenCount = hiddenNodes.length;

    for (var i = 0, j = hiddenNodes.length; i < j; i++) {
        var n = hiddenNodes[i];
        if (isForceHidden(n)) {
            hiddenCount -= 1;
        } else if (n.isParent) {
            hiddenCount += zTree.transformToArray(n.children).length;
        }
    }

    $("#isHiddenCount").text(hiddenNodes.length);
    $("#hiddenCount").text(hiddenCount);
    $("#checkCount").text(checkCount);
    $("#nocheckCount").text(nocheckCount);
}
function showNodes() {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
        .getNodesByParam("isHidden", true);
    zTree.showNodes(nodes);
    setTitle();
    count();
}
function hideNodes() {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
        .getSelectedNodes();
    if (nodes.length == 0) {
        alert("请至少选择一个节点");
        return;
    }
    zTree.hideNodes(nodes);
    setTitle();
    count();
}
$("#saveRoleResource").click(function () {
    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
    var nodes = treeObj.getCheckedNodes(true);
    var newNodes = new Array();

    for (var i = 0; i < nodes.length; i++) {
        var newNode = new Object();
        newNode.roleId = roleId;
        newNode.resourceNum = nodes[i].resourceNum;
        newNodes.push(newNode);
    }
    var jsonStr = JSON.stringify(newNodes);
    var requestData = new Object();
    requestData.roleId = roleId;
    requestData.roleResourceInfoList = jsonStr;
    commonAjax(updateRoleResourceRelationUrl, requestData, "nodeUpdateRelation", "POST")
});

function nodeUpdateRelation(data) {
    if (data === "SUCCESS") {
        alert("更新成功");
        window.location.reload();
    } else {
        alert("更新失败请重试");
    }
}