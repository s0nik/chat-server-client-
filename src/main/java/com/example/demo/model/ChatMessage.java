/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nik
 */
@Entity
@Table(name = "chat_message")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChatMessage.findAll", query = "SELECT c FROM ChatMessage c")
    , @NamedQuery(name = "ChatMessage.findByMessageId", query = "SELECT c FROM ChatMessage c WHERE c.messageId = :messageId")
    , @NamedQuery(name = "ChatMessage.findByMessageFrom", query = "SELECT c FROM ChatMessage c WHERE c.messageFrom = :messageFrom")
    , @NamedQuery(name = "ChatMessage.findByTime", query = "SELECT c FROM ChatMessage c WHERE c.time = :time")
    , @NamedQuery(name = "ChatMessage.findByStatus", query = "SELECT c FROM ChatMessage c WHERE c.status = :status")})
public class ChatMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "message_id")
    private Integer messageId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "message_from")
    private String messageFrom;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "message")
    private String message;
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;
    @JoinColumn(name = "chat_id", referencedColumnName = "chat_id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Chat chatId;

    public ChatMessage() {
    }

    public ChatMessage(Integer messageId) {
        this.messageId = messageId;
    }

    public ChatMessage(Integer messageId, String messageFrom, String message, boolean status) {
        this.messageId = messageId;
        this.messageFrom = messageFrom;
        this.message = message;
        this.status = status;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageFrom() {
        return messageFrom;
    }

    public void setMessageFrom(String messageFrom) {
        this.messageFrom = messageFrom;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Chat getChatId() {
        return chatId;
    }

    public void setChatId(Chat chatId) {
        this.chatId = chatId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messageId != null ? messageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChatMessage)) {
            return false;
        }
        ChatMessage other = (ChatMessage) object;
        if ((this.messageId == null && other.messageId != null) || (this.messageId != null && !this.messageId.equals(other.messageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.model.ChatMessage[ messageId=" + messageId + " ]";
    }
    
}
