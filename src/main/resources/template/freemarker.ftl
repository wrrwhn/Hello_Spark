<html>
<head>
    <style>
        * {
            font-weight: normal;
            font-family: arial;
            box-sizing: border-box;
        }

        body {
            margin: 20px;
        }

        ul {
            margin-top: 0;
            padding: 0;
        }

        .post {
            padding: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin: 15px 0;
            background: #FCFCFC;
        }

        .post h2 {
            margin-top: 0;
            padding-bottom: 15px;
            border-bottom: 1px solid #ddd;
        }

        .post h3 {
            font-size: 16px;
            margin-bottom: 5px;
        }

        .categories li {
            display: inline-block;
        }

        .categories li:after {
            content: ",";
        }

        .categories li:first-child{
            margin-left: 15px;
        }

        .categories li:last-child:after {
            content: "";
        }
    </style>
</head>
<body>
<h1>Hello FreeMarker</h1>

<#list list as article>
<div class="post">
    <h2>${article.title}</h2>

    <p>${article.content}</p>

    <h3>Categories:</h3>
    <ul class="categories">
        <#list article.categories as category>
            <li>${category}</li>
        </#list>
    </ul>
    <i></i>
</div>
</#list>

</body>
</html>
