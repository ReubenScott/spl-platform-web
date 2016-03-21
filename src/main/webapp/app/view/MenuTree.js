Ext.define('epl.view.MenuTree',{ 
    extend: 'Ext.tree.Panel', 
    alias: 'widget.menuTree',
    requires:['epl.store.MenuStore'], 
    initComponent : function(){ 
        Ext.apply(this,{ 
            id: 'menu-panel', 
            title: '系统菜单', 
            iconCls:'icon-menu', 
            margins : '0 0 -1 1', 
            region:'west', 
            border : false, 
            enableDD : false, 
            split: true, 
            width : 212, 
            minSize : 130, 
            maxSize : 300, 
            rootVisible: false, 
            containerScroll : true, 
            collapsible : true, 
            autoScroll: false,
            store:Ext.create('epl.store.MenuStore')
        }); 
        this.callParent(arguments); 
    } 
}); 
