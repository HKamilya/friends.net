<#ftl encoding="UTF-8"/>
<#import "header.ftl" as base>

<@base.main>
    <#if user??>
        <div style="background: #6b6b6b">
            <form name="form" action="AddEvent" method="post" onsubmit="return validate()"
                  enctype="multipart/form-data">
                <table align="center">
                    <tr>
                        <td>Название</td>
                        <td><label>
                                <input type="text" name="name" required/>
                            </label></td>
                    </tr>
                    <tr>
                        <td>Город</td>
                        <td><label>
                                <input type="text" name="city" required/>
                            </label></td>
                    </tr>
                    <tr>
                        <td>Улица</td>
                        <td><label>
                                <input type="text" name="street"/>
                            </label></td>
                    </tr>
                    <tr>
                        <td>Дом</td>
                        <td><label>
                                <input type="text" name="house"/>
                            </label></td>
                    </tr>
                    <tr>
                        <td>Дата</td>
                        <td><label>
                                <input type="date" name="date" required/>
                            </label></td>
                    </tr>
                    <tr>
                        <td>Фотография</td>
                        <td><label>
                                <input type="file" name="image" accept="image/*" required/>
                            </label></td>
                    </tr>
                    <tr>
                        <td>Описание</td>
                        <td>
                            <textarea rows="5" type="text" name="description" required/></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>Категория</td>
                        <td>
                            <label>
                                <select name="category"><#list list as categories>
                                        <option name="category_id" value="${categories.id}">${categories.name}
                                        </option>
                                    </#list>
                                </select>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td><#if errMessage??>
                                ${errMessage}
                            </#if>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Добавить"></input><input
                                    type="reset" value="Сбросить"></input></td>
                    </tr>
                </table>
            </form>
        </div>
    <#else >
        <div onload="location.href='/Login'"></div>
    </#if>
</@base.main>