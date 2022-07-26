<#import "partials/sportsman_main.ftlh" as main />
<@main.renderWith scripts=["/js/ckeditor/ckeditor.js"]>
   <h2>Здравствуйту ${sportsman.name}</h2>


   <h3>Программа занятий</h3>
   <#if programMessage??>
      <span style="color: red">
                ${programMessage} </span><br>
   </#if>

<#--   <form action="${sportsman.id}/trainingProgram" method="post">-->
<#--      <div>-->
<#--      <textarea cols="80" rows="10" id="content" name="content">-->
<#--          <#if event.trainingProgram??>-->
<#--             ${event.trainingProgram}-->
<#--          </#if>-->
<#--      </textarea>-->
<#--         <script type="text/javascript">-->
<#--            CKEDITOR.replace('content');-->
<#--         </script>-->
<#--         <button type="submit" class="mt-3 bn30 mb-3">Отправить</button>-->
<#--      </div>-->
<#--   </form>-->

</@main.renderWith>