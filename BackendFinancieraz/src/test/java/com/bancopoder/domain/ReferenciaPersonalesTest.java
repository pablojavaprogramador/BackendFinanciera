package com.bancopoder.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.bancopoder.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ReferenciaPersonalesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReferenciaPersonales.class);
        ReferenciaPersonales referenciaPersonales1 = new ReferenciaPersonales();
        referenciaPersonales1.setId(1L);
        ReferenciaPersonales referenciaPersonales2 = new ReferenciaPersonales();
        referenciaPersonales2.setId(referenciaPersonales1.getId());
        assertThat(referenciaPersonales1).isEqualTo(referenciaPersonales2);
        referenciaPersonales2.setId(2L);
        assertThat(referenciaPersonales1).isNotEqualTo(referenciaPersonales2);
        referenciaPersonales1.setId(null);
        assertThat(referenciaPersonales1).isNotEqualTo(referenciaPersonales2);
    }
}
