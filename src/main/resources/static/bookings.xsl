<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" encoding="UTF-8" indent="yes"/>

<xsl:template match="/bookings">
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Бронирования</title>
    <link rel="stylesheet" href="/style.css"/>
</head>
<body>
    <nav>
        <a href="/api/clients">Все клиенты</a>
        <a href="/api/bookings">Все бронирования</a>
    </nav>

    <h2>Бронирования (<xsl:value-of select="count(item)"/>)</h2>

    <xsl:choose>
        <xsl:when test="count(item) = 0">
            <p>Бронирований нет.</p>
        </xsl:when>
        <xsl:otherwise>
            <table>
                <tr><th>ID</th><th>Клиент</th><th>Заезд</th><th>Выезд</th><th>Комната</th><th>Статус</th></tr>
                <xsl:for-each select="item">
                    <tr>
                        <td><a href="/api/bookings/{id}"><xsl:value-of select="id"/></a></td>
                        <td><a href="/api/clients/{client/id}"><xsl:value-of select="client/name"/></a></td>
                        <td><xsl:value-of select="checkIn"/></td>
                        <td><xsl:value-of select="checkOut"/></td>
                        <td><xsl:value-of select="roomNumber"/></td>
                        <td><xsl:value-of select="status"/></td>
                    </tr>
                </xsl:for-each>
            </table>
        </xsl:otherwise>
    </xsl:choose>
</body>
</html>
</xsl:template>
</xsl:stylesheet>
