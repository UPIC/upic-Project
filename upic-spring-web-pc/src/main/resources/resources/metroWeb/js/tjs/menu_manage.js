var getAllResourceUrl = "/operator/listResource";

var getResourceById = "/operator/getResourceById";

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
    commonAjax(getAllResourceUrl, requestData, "getAllResource", "GET");
})

function getAllResource(datas) {
    for (var i = 0; i < datas.length; i++) {
        var zNode = {
            id: 0,
            pId: 0,
            name: "",
            isParent: true
        };
        zNode.id = datas[i].id;
        zNode.name = datas[i].resourceName;
        zNode.pId = datas[i].fatherId;
        zNode.isParent = isLeaf(datas[i].isLeaf);

        zNode.myId = datas[i].id;
        zNode.resourceName = datas[i].resourceName;
        zNode.url = datas[i].url;
        zNode.status = datas[i].status;
        zNode.type = datas[i].type;
        zNode.level = datas[i].level;
        zNode.myFatherId = datas[i].fatherId;

        zNodes.push(zNode);
    }

    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
}

function zTreeOnClick(event, treeId, treeNode) {
    commonAjax(getResourceById, "id=" + treeNode.myId, "getResource", "GET");
}

function getResource(data) {
    var htmls = "";
    htmls += "<form action='#' class='form-horizontal'>";
    htmls += "<div class='control-group'><label class='control-label'>上级菜单:</label>";
    htmls += "<div class='controls'><input type='text' class='input-large' disabled='disabled' value='" + data.fatherId + "'/>";
    htmls += "</div></div><div class='control-group'><label class='control-label'>菜单编号:</label>";
    htmls += "<div class='controls'><input type='text' class='input-large' disabled='disabled' value='" + data.id + "'/></div></div>";
    htmls += "<div class='control-group'><label class='control-label'>url:</label><div class='controls'>";
    htmls += "<input type='text' class='input-large' value='" + data.url + "'/></div></div><div class='form-bottom'>";
    htmls += "<button class='btn btn-mid btn-info'>保存</button></div></form>";
    $("#menuManage").html(htmls);
}

function isLeaf(leaf) {
    return (leaf == 0 ? true : false);
}