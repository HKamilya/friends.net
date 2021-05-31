<#ftl encoding="UTF-8"/>
<#import "header.ftlh" as base>
<#import  "spring.ftl" as spring/>

<@base.main>

    <div style="background: #6b6b6b; height: 100%">
        <br>
        <br>
        <form name="form" action="/addEvent" method="post" enctype="multipart/form-data">
            <table align="center">
                <tr>
                    <td><@spring.message 'add_event_page.form.name'/></td>
                    <td><label>
                            <input type="text" name="name" id="name" required/>
                        </label></td>
                </tr>
                <tr>
                    <td><@spring.message 'add_event_page.form.city'/></td>
                    <td><label>
                            <input type="text" name="city" id="city" required/>
                        </label></td>
                </tr>
                <tr>
                    <td><@spring.message 'add_event_page.form.street'/></td>
                    <td><label>
                            <input type="text" name="street" id="street"/>
                        </label></td>
                </tr>
                <tr>
                    <td><@spring.message 'add_event_page.form.house'/></td>
                    <td><label>
                            <input type="text" name="house" id="house"/>
                        </label></td>
                </tr>
                <tr>
                    <td><@spring.message 'add_event_page.form.date'/></td>
                    <td><label>
                            <input type="date" name="date" id="date" required/>
                        </label></td>
                </tr>
                <tr>
                    <td><@spring.message 'add_event_page.form.time'/></td>
                    <td><label>
                            <input type="time" name="time" id="time" required/>
                        </label></td>
                </tr>
                <tr>
                    <td><@spring.message 'add_event_page.form.picture'/></td>
                    <td><label>
                            <input type="file" name="image" accept="image/*" id="image" required/>
                        </label></td>
                </tr>
                <tr>
                    <td><@spring.message 'add_event_page.form.description'/></td>
                    <td>
                        <label>
                            <textarea rows="5" name="description" id="description" required></textarea>
                        </label>
                    </td>
                </tr>

                <tr>
                    <td><@spring.message 'add_event_page.form.category'/></td>
                    <td>
                        <label>
                            <select name="category"><#list categoryList as categories>
                                    <option name="category_id" value="${categories.id}">${categories.name}</option>
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
                    <td><input type="submit" value="<@spring.message 'add_event_page.form.add'/>">
                        <input type="reset" value="<@spring.message 'add_event_page.form.reset'/>"></td>
                </tr>
            </table>
        </form>
    </div>
</@base.main>