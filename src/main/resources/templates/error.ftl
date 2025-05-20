<#assign context = springMacroRequestContext.contextPath/>
<#import "/spring.ftl" as spring/>

<#include "layout/common/_app-style.ftl">
<h1 class="text-center text-danger">${errorCode!""}</h1>
<p class="text-warning text-center">${errorDescription}</p>
<h5 class="text-center"><a href="#" id="btnBack" onclick="back()">Back</a></h5>
<#include "layout/common/_app-script.ftl">