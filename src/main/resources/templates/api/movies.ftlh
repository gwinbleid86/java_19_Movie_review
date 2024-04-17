<#import "../layout.ftlh" as main>
<@main.layout>


    <div class="m-5">
        <nav aria-label="...">
            <ul id="pagination" class="pagination">
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
                </li>
                <#--                <li class="page-item">-->
                <#--                    <a class="page-link" href="#">1</a>-->
                <#--                </li>-->
                <#--                <li class="page-item active" aria-current="page">-->
                <#--                    <a class="page-link" href="#">2</a>-->
                <#--                </li>-->
                <#--                <li class="page-item">-->
                <#--                    <a class="page-link" href="#">3</a>-->
                <#--                </li>-->
                <li id="next" class="page-item">
                    <a class="page-link" href="#" tabindex="+1">Next</a>
                </li>
            </ul>
        </nav>
    </div>

    <div id="content"></div>


    <script src="../../static/js/script.js"></script>
</@main.layout>