package com.liyv.portal.pojo;

import org.apache.commons.lang3.StringUtils;

public class PortalItem extends TaoItem {

    public String[] getImages() {
        String images = super.getImage();
        if (!StringUtils.isEmpty(images)) {
            String[] imgs = images.split(",");
            return imgs;
        }
        return null;
    }
}
