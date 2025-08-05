<section class="sidebar">
    <!-- Sidebar user panel -->
    <div class="user-panel">
        <div class="pull-left image">
            <img src="${context}/assets/images/user-icon.png" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
            <p>${auth.name}</p>
            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
    </div>

    <!-- /changeLangForm-->
    <form action="#" method="get" class="sidebar-form" id="changeLangForm">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <select class="form-control changeLang" id="lang" name="lang">
            <option value="en" <#if .locale == "en">selected</#if>><@spring.message "common.label.langEnglish"/></option>
            <option value="in" <#if .locale == "in">selected</#if>><@spring.message "common.label.langIndonesian"/></option>
        </select>
    <#--<div class="input-group">

        <span class="input-group-btn">
            <button type="button" name="search" id="changeLang" class="btn btn-flat"><i class="fa fa-check-square"></i>
            </button>
          </span>
        </div>-->
    </form>
    <#--end change lang form-->

    <!-- sidebar menu: : style can be found in sidebar.less -->
    <ul class="sidebar-menu" data-widget="tree">
        <li class="" id="dashboard">
            <a href="/home">
                <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                <span class="pull-right-container"></span>
            </a>
        </li>
        <li class="header">MASTER</li>
        <li>
            <a href="${context}/master/book/list"><i class="fa fa-book"></i> <span>Books</span></a>
        </li>
        <li class="header">SYSTEM</li>
        <li>
            <a href="${context}/system/application_config/list"><i class="fa fa-cog"></i> <span><@spring.message "page.applicationConfig.title"/></span></a>
        </li>
        <li>
            <a href="${context}/system/app_user/list"><i class="fa fa-user"></i> <span><@spring.message "page.applicationUser.title"/></span></a>
        </li>
    </ul>
</section>