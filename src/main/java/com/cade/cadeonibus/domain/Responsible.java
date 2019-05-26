package com.cade.cadeonibus.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "RESPONSIBLE")
public class Responsible extends PersonAbstract{
}
