    <!-- Logo -->
        <a href="${context}/index" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b><@spring.message "application.title1.initial"/></b><@spring.message "application.title2.initial"/></span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b><@spring.message "application.title1"/></b> <@spring.message "application.title2"/></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>

            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <#--<li>
                        <a href="#" onclick="printPage()" title="<@spring.message "label.common.print.thisPage"/>" style="padding-bottom:20px;"><i class="fa fa-print" ></i> </a>
                    </li>-->
                    <!-- User Account: style can be found in dropdown.less -->
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="${context}/assets/images/user-icon.png" class="user-image" alt="User Image">
                            <span class="hidden-xs">${auth.name}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header">
                                <img src="${context}/assets/images/user-icon.png" class="img-circle" alt="User Image">

                                <p>
                                    <#--${user.firstName!""}&nbsp;${user.lastName!""}-->
                                    <small></small>
                                </p>
                            </li>
                            <!-- Menu Body -->
                            <li class="user-body">
                                <!-- /.row -->
                            </li>
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-left">
                                    <a href="${context}/profile" class="btn btn-default btn-flat">Profile</a>
                                </div>
                                <div class="pull-right">
                                    <a href="${context}/logout" class="btn btn-default btn-flat">Sign out</a>
                                </div>
                            </li>


                        </ul>
                    </li>
                </ul>
            </div>
        </nav>