> **Attention**  
> This repository contains many vulnerabilities intentionally, and thus should not be used as a base for another project. You have been warned.

## Vulnerabilities

### 1. Sensitive data exposure (OWASP A3:2017)

In this application sensitive data is exposed in multiple ways.  
**Vulnerability:** First and foremost, the password for the database is hardcoded into the public repository.
This can be identified by searching "password" in the repository.

Hard-coding credientials can lead to large data-breaches.

**Fix:** The database password should be placed into a environment variable or into a config file so that the user can change it. 
If using latter method, prevent users from using the default password.

**Vulnerability:** Registered addresses are displayed to anyone. This can be identified by signing up for the event.
Addresses are Personal Data according to GDPR and exposure could lead to serious fines to the company and trouble for the users.

**Fix:** Do not display addresses of attendees after a successful sign up. 
The addresses should only be displayed to the organizer of the event and only if necessary.

### 2. Injection (OWASP A1:2017)

**Vulnerability:** SQL Injection is possible in either name or address field of /form page.
The issue can be identified by submitting the form:
" '-- " This results in an error page showing sql injection vulnerability.

This vulnerability is caused by the user input being directly appended to the SQL without escaping it.

**Fix:** Any user input should be validated and escaped when entering it into an SQL Statement.
In Java the use of PreparedStatement automatically escapes user input.

### 3. Insufficient logging (OWASP A10:2017)

**Vulnerability:** Possible probes that attempt to penetrate the security of the service are not logged.
Any actions performed on the service, including accessing and sending forms is not logged.
This means that any attacker can come and go undetected.

**Fix:** Generate access logs and log any operation the program performs. 
In case of this application, logging the form submit action.

### 4. Broken Access control (OWASP A5:2017)

**Vulnerability:** Anyone who knows the /done page can access it, exposing the addresses of (1.) to a wider audience.
Users without permissions to view certain pages should not be able to access the pages.
In case of this application, the /done page should not be accessible without filling in the form.

**Fix:** Sessions should be managed via Cookies. This would allow denying access to any users who have not sent the form.
Currently the framework sends cookies, but access control is not enforced.

### 5. Cross-Site Scripting (XSS) (OWASP A7:2017)

**Vulnerability:** User submitted form information is displayed to the user without escaping html entities. This means that any attacker can execute malicious code when the user enters the /done page.

This vulnerability can be identified by entering "`<script>alert("Hey");</script>`" to a field on the /form page, leading to the script being executed when the page is redirected.

**Fix:** The issue arises in this program due to use of "th:utext" in Thymeleaf, which means that thymeleaf does not escape the content. The issue can be fixed by escaping the input with "th:text"