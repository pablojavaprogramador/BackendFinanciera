package com.bancopoder.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.bancopoder.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EmpleosTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Empleos.class);
        Empleos empleos1 = new Empleos();
        empleos1.setId(1L);
        Empleos empleos2 = new Empleos();
        empleos2.setId(empleos1.getId());
        assertThat(empleos1).isEqualTo(empleos2);
        empleos2.setId(2L);
        assertThat(empleos1).isNotEqualTo(empleos2);
        empleos1.setId(null);
        assertThat(empleos1).isNotEqualTo(empleos2);
    }
}
