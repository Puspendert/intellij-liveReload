package com.sample.common;

import java.io.Serializable;

/**
 * parent interface for Entity
 * @author puspender
 *
 */
public interface ParentEntity<ID> extends Serializable{

    ID getId();

}
