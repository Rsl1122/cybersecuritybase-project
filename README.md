> **Attention**  
> This repository contains many vulnerabilities intentionally, and thus should not be used as a base for another project. You have been warned.

## Vulnerabilities

### 1. Sensitive data exposure (OWASP A3:2017)

In this application sensitive data is exposed in multiple ways.  
**Vulnerability:** First and foremost, the password for the database is hardcoded into the public repository.
This can be identified by searching "password" in the repository.

**Fix:** Place the database password into a System variable or into a config file so that the user can change it. 
If using latter method, prevent users from using the default password.

**Vulnerability:** Registered addresses are displayed to anyone. This can be identified by signing up for the event.
Addresses are Personal Data according to GDPR and could lead to serious fines.

**Fix:** Do not display addresses of attendees after a successful sign up.

### 2. Injection (OWASP A1:2017)

**Vulnerability:** SQL Injection is possible in either name or address field of /form page.
The issue can be identified by submitting the form:

to be continued..