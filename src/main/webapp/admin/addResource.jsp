
<%@include file="/taglib.jsp"%>
<jsp:include page="../head.jsp"/>
<title>Add Resource</title>

<jsp:include page="../header.jsp"/>

<div class="login-container">
    <h1 class="text-center pad-bottom-sm">Add A New Resource</h1>


    <!--TODO form verification! auto complete attribute -->

    <form action="${pageContext.request.contextPath}/addResource"  method="post" autocomplete="on">
        <div class="form-group">
            <label for="name">Resource Name</label>
            <input type="text" class="form-control" id="name" name ="name" placeholder="Give this resource a name"  aria-required="true" required>
        </div>
        <div class="form-group">//TODO fix this so fields are populated by db and only one cn be selected
            <input type="radio" id="foodPantry" name="foodPantry" value=1>
            <label for="foodPantry">Food Pantry</label><br>
            <input type="radio" id="freeLP" name="freeLP" value=2>
            <label for="freeLP">Free Little Pantry</label><br>
            <input type="radio" id="comMeal" name="comMeal" value=3>
            <label for="comMeal">Community Meal</label>
            <input type="radio" id="gov" name="gov" value=4>
            <label for="gov">Government Resource or Program</label>
            <input type="radio" id="comAid" name="comAid" value=5>
            <label for="comAid">Community Aid and Support Group</label>
            <input type="radio" id="other" name="other" value=6>
            <label for="other">Other type not listed</label>
        </div>

        <div class="form-group">
            <label for="owner">Resource Owner(optional field to connect different food resources with a common owner)
            //TODO drown menu of resource owners set in the servlet
                //https://www.codejava.net/java-ee/jsp/how-to-create-dynamic-drop-down-list-in-jsp-from-database
            <select name="owner" id="owner">
                <c:forEach items="${listOwner}" var="owner">
                    <option value="${owner.id}">${owner.name}</option>
                </c:forEach>
            </select>
            </label>
        </div>
        <!--TODO form verification requirements for pw verification?   put the dots in instead of letters? no-->
        <div class="form-group">
            <label for="user_password">Password</label>
            <input type="text" class="form-control" id="user_password" name ="password"placeholder="Password(password specs here)" autocomplete="new-password" aria-required="true" required>
        </div>
        <!--TODO form verification ensure it's a valid email address-->
        <div class="form-group">
            <label for="email_address">Email address</label>
            <input type="email" class="form-control" id="email_address" name ="email" placeholder="Email"  autocomplete="email" aria-required="true" required>
        </div>

        <hr />
        <button type="submit" class="btn btn-primary btn-lg">Submit</button>
    </form>




</div>
<jsp:include page="../footer.jsp"/>