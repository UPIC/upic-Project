var getAllProjectCategoryUrl = "/common/getAllProjectCategory";
var getAllCategoryNodeUrl = "/common/searchCategoryNodeList";

var requestData = {};

var zNodes = new Array();

var setting = {
    view: {
        selectedMulti: false
    },
    edit: {
        enable: true,
        showRemoveBtn: false,
        showRenameBtn: false
    },
    data: {
        keep: {
            parent: true,
            leaf: true
        },
        simpleData: {
            enable: true
        }
    },
    callback: {
        beforeDrag: beforeDrag,
        beforeRemove: beforeRemove,
        beforeRename: beforeRename,
        onRemove: onRemove,
        onClick: zTreeOnClick
    }
};

$(function () {
    initMyTree();
})

function initMyTree() {
    zNodes = null;
    zNodes = new Array();
    commonAjax(getAllCategoryNodeUrl, requestData, "addAllCategoryNodeUrl", "GET");
    aAjax(getAllProjectCategoryUrl);
}

function aAjax(url) {
    $.ajax({
        type: "GET",
        url: url,
        data: requestData,
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (result) {
            addAllProjectCategoryUrl(result);
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}

function addAllProjectCategoryUrl(datas) {
    for (var i = 0; i < datas.content.length; i++) {
        if (i === 0) {
            var zNode = {id: 0, pId: 0, name: "", isParent: true};
            zNode.id = datas.content[0].id;
            zNode.name = datas.content[0].categoryName;
            zNode.pId = 0;
            zNode.myParentId = 0;
            zNode.open = false;
            zNode.isParent = true;
            zNode.myId = datas.content[0].id;
            zNode.level = 0;
            zNodes.push(zNode);
        } else {
            var zNode = {id: 0, pId: 0, name: "", isParent: true};
            zNode.id = datas.content[i].id;
            zNode.name = datas.content[i].categoryName;
            zNode.myParentId = 0;
            zNode.pId = 0;
            zNode.myId = datas.content[i].id;
            zNode.level = 0;
            zNode.isParent = true;
            zNodes.push(zNode);
        }
    }
    if (zNodes.length === datas.content.length) {

    } else {
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    }
}

function addAllCategoryNodeUrl(datas) {
    var zNodesLength = 200;
    for (var i = 0; i < datas.length; i++) {
        if (datas[i].level == 1) {
            var zNode = {id: 0, pId: 0, name: "", isParent: true};
            zNode.id = datas[i].id + zNodesLength;
            zNode.name = datas[i].nodeContent;
            zNode.myParentId = datas[i].fatherId;
            zNode.pId = datas[i].fatherId;
            zNode.myId = datas[i].id;
            zNode.isParent = isLeaf(datas[i].isLeaf);
            zNode.level = 1;
            zNodes.push(zNode);
        } else {
            var zNode = {id: 0, pId: 0, name: "", isParent: true};
            zNode.id = datas[i].id + zNodesLength;
            zNode.name = datas[i].nodeContent;
            zNode.myParentId = datas[i].fatherId + zNodesLength;
            zNode.pId = datas[i].fatherId + zNodesLength;
            zNode.isParent = isLeaf(datas[i].isLeaf);
            zNode.myId = datas[i].id;
            zNode.level = datas[i].level;
            zNodes.push(zNode);
        }
    }
    if (zNodes.length === datas.length) {

    } else {
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    }
}

function getProjectCategory(id) {
    $.ajax({
        type: "GET",
        url: "/common/getProjectCategoryById",
        data: {
            id: id
        },
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (result) {
            addProjectCategoryHtml(result);
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}

function getCategoryNode(id) {
    $.ajax({
        type: "GET",
        url: "/common/getCategoryNodeById",
        data: {
            id: id
        },
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (result) {
            addCategoryNodeHtml(result);
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}

function addProjectCategoryHtml(data) {
    var htmls = "";
    htmls += "<div class='control-group'>";
    htmls += "<label class='control-label'>上级编号:</label>";
    htmls += "<div class='controls'>";
    htmls += "<input type='text' class='input-large' readonly value='无'/>";
    htmls += "</div></div><div class='control-group'><label class='control-label'>菜单编号:</label>"
    htmls += "<div class='controls'><input type='text' id='nodeId'class='input-large' readonly value='" + data.id + "'/></div></div>"
    htmls += "<div class='control-group'><label class='control-label'>节点内容:</label>"
    htmls += "<div class='controls'><input id='node_content' type='text' class='input-large' value='" + data.categoryName + "'/></div></div>"
    htmls += "<div class='form-bottom'><button class='btn btn-mid btn-info' ><span onclick='saveProjectCategory(" + data.id + ")'>保存</span></button></div>";

    $("#node_form").html(htmls);
}

function saveProjectCategory(id) {
    $.ajax({
        type: "GET",
        url: "/common/updateProjectCategory",
        data: {
            id: id,
            categoryName: $("#node_content").val(),
        },
        success: function (result) {
            alert("保存成功");
            initMyTree();
        }
    });

}

function addCategoryNodeHtml(data) {
    var htmls = "";
    htmls += "<div class='control-group'>";
    htmls += "<label class='control-label'>上级编号:</label>";
    htmls += "<div class='controls'>";
    htmls += "<input type='text' class='input-large' readonly value='" + data.fatherId + "'/>";
    htmls += "</div></div><div class='control-group'><label class='control-label'>菜单编号:</label>"
    htmls += "<div class='controls'><input type='text' class='input-large' readonly value='" + data.id + "'/></div></div>"
    htmls += "<div class='control-group'><label class='control-label'>节点内容:</label>"
    htmls += "<div class='controls'><input id='node_content' type='text' class='input-large' value='" + data.nodeContent + "'/></div></div>"
    htmls += "<div class='form-bottom'><button class='btn btn-mid btn-info' ><span onclick='saveCategoryNode(" + data.id + ")'>保存</span></button></div>";

    $("#node_form").html(htmls);
}

function saveCategoryNode(id) {
    $.ajax({
        type: "GET",
        url: "/common/updateCategoryNode",
        data: {
            id: id,
            nodeContent: $("#node_content").val(),
        },
        success: function (result) {
            alert("保存成功");
        }
    });
}

function isLeaf(leaf) {
    return (leaf == 0 ? true : false);
}

function zTreeOnClick(event, treeId, treeNode) {
    if (treeNode.myParentId === 0) {
        getProjectCategory(treeNode.myId);
    } else {
        getCategoryNode(treeNode.myId);
    }
}