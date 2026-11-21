<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" encoding="UTF-8" indent="yes"/>

<xsl:template match="/clients">
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Клиенты</title>
    <link rel="stylesheet" href="/style.css"/>
</head>
<body>
    <nav>
        <a href="/api/clients">Все клиенты</a>
        <a href="/api/bookings">Все бронирования</a>
    </nav>

    <h2>Клиенты (<xsl:value-of select="count(item)"/>)</h2>
    <table>
        <tr><th>ID</th><th>Имя</th><th>Телефон</th><th>Email</th><th>Бронирования</th></tr>
        <xsl:for-each select="item">
            <tr>
                <td><xsl:value-of select="id"/></td>
                <td><a href="/api/clients/{id}"><xsl:value-of select="name"/></a></td>
                <td><xsl:value-of select="phone"/></td>
                <td><xsl:value-of select="email"/></td>
                <td><a href="/api/clients/{id}/bookings">открыть</a></td>
            </tr>
        </xsl:for-each>
    </table>
</body>
</html>
</xsl:template>
</xsl:stylesheet>
