package com.bancopoder.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bancopoder.IntegrationTest;
import com.bancopoder.domain.Domicilio;
import com.bancopoder.repository.DomicilioRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link DomicilioResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DomicilioResourceIT {

    private static final String DEFAULT_CALLE = "AAAAAAAAAA";
    private static final String UPDATED_CALLE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_EXTERIOR = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_EXTERIOR = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_INTERIOR = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_INTERIOR = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_POSTAL = "AAAAAAAAAA";
    private static final String UPDATED_CODE_POSTAL = "BBBBBBBBBB";

    private static final String DEFAULT_DOMICILIO_ACTUAL = "AAAAAAAAAA";
    private static final String UPDATED_DOMICILIO_ACTUAL = "BBBBBBBBBB";

    private static final String DEFAULT_MANZANA = "AAAAAAAAAA";
    private static final String UPDATED_MANZANA = "BBBBBBBBBB";

    private static final String DEFAULT_ANDADOR = "AAAAAAAAAA";
    private static final String UPDATED_ANDADOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDIFICIO = "AAAAAAAAAA";
    private static final String UPDATED_EDIFICIO = "BBBBBBBBBB";

    private static final String DEFAULT_FECHA_ANTIGUEDAD = "AAAAAAAAAA";
    private static final String UPDATED_FECHA_ANTIGUEDAD = "BBBBBBBBBB";

    private static final String DEFAULT_CODIGO_POSTAL = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO_POSTAL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/domicilios";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DomicilioRepository domicilioRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDomicilioMockMvc;

    private Domicilio domicilio;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Domicilio createEntity(EntityManager em) {
        Domicilio domicilio = new Domicilio()
            .calle(DEFAULT_CALLE)
            .numeroExterior(DEFAULT_NUMERO_EXTERIOR)
            .numeroInterior(DEFAULT_NUMERO_INTERIOR)
            .codePostal(DEFAULT_CODE_POSTAL)
            .domicilioActual(DEFAULT_DOMICILIO_ACTUAL)
            .manzana(DEFAULT_MANZANA)
            .andador(DEFAULT_ANDADOR)
            .edificio(DEFAULT_EDIFICIO)
            .fechaAntiguedad(DEFAULT_FECHA_ANTIGUEDAD)
            .codigoPostal(DEFAULT_CODIGO_POSTAL);
        return domicilio;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Domicilio createUpdatedEntity(EntityManager em) {
        Domicilio domicilio = new Domicilio()
            .calle(UPDATED_CALLE)
            .numeroExterior(UPDATED_NUMERO_EXTERIOR)
            .numeroInterior(UPDATED_NUMERO_INTERIOR)
            .codePostal(UPDATED_CODE_POSTAL)
            .domicilioActual(UPDATED_DOMICILIO_ACTUAL)
            .manzana(UPDATED_MANZANA)
            .andador(UPDATED_ANDADOR)
            .edificio(UPDATED_EDIFICIO)
            .fechaAntiguedad(UPDATED_FECHA_ANTIGUEDAD)
            .codigoPostal(UPDATED_CODIGO_POSTAL);
        return domicilio;
    }

    @BeforeEach
    public void initTest() {
        domicilio = createEntity(em);
    }

    @Test
    @Transactional
    void createDomicilio() throws Exception {
        int databaseSizeBeforeCreate = domicilioRepository.findAll().size();
        // Create the Domicilio
        restDomicilioMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(domicilio)))
            .andExpect(status().isCreated());

        // Validate the Domicilio in the database
        List<Domicilio> domicilioList = domicilioRepository.findAll();
        assertThat(domicilioList).hasSize(databaseSizeBeforeCreate + 1);
        Domicilio testDomicilio = domicilioList.get(domicilioList.size() - 1);
        assertThat(testDomicilio.getCalle()).isEqualTo(DEFAULT_CALLE);
        assertThat(testDomicilio.getNumeroExterior()).isEqualTo(DEFAULT_NUMERO_EXTERIOR);
        assertThat(testDomicilio.getNumeroInterior()).isEqualTo(DEFAULT_NUMERO_INTERIOR);
        assertThat(testDomicilio.getCodePostal()).isEqualTo(DEFAULT_CODE_POSTAL);
        assertThat(testDomicilio.getDomicilioActual()).isEqualTo(DEFAULT_DOMICILIO_ACTUAL);
        assertThat(testDomicilio.getManzana()).isEqualTo(DEFAULT_MANZANA);
        assertThat(testDomicilio.getAndador()).isEqualTo(DEFAULT_ANDADOR);
        assertThat(testDomicilio.getEdificio()).isEqualTo(DEFAULT_EDIFICIO);
        assertThat(testDomicilio.getFechaAntiguedad()).isEqualTo(DEFAULT_FECHA_ANTIGUEDAD);
        assertThat(testDomicilio.getCodigoPostal()).isEqualTo(DEFAULT_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    void createDomicilioWithExistingId() throws Exception {
        // Create the Domicilio with an existing ID
        domicilio.setId(1L);

        int databaseSizeBeforeCreate = domicilioRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDomicilioMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(domicilio)))
            .andExpect(status().isBadRequest());

        // Validate the Domicilio in the database
        List<Domicilio> domicilioList = domicilioRepository.findAll();
        assertThat(domicilioList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDomicilios() throws Exception {
        // Initialize the database
        domicilioRepository.saveAndFlush(domicilio);

        // Get all the domicilioList
        restDomicilioMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(domicilio.getId().intValue())))
            .andExpect(jsonPath("$.[*].calle").value(hasItem(DEFAULT_CALLE)))
            .andExpect(jsonPath("$.[*].numeroExterior").value(hasItem(DEFAULT_NUMERO_EXTERIOR)))
            .andExpect(jsonPath("$.[*].numeroInterior").value(hasItem(DEFAULT_NUMERO_INTERIOR)))
            .andExpect(jsonPath("$.[*].codePostal").value(hasItem(DEFAULT_CODE_POSTAL)))
            .andExpect(jsonPath("$.[*].domicilioActual").value(hasItem(DEFAULT_DOMICILIO_ACTUAL)))
            .andExpect(jsonPath("$.[*].manzana").value(hasItem(DEFAULT_MANZANA)))
            .andExpect(jsonPath("$.[*].andador").value(hasItem(DEFAULT_ANDADOR)))
            .andExpect(jsonPath("$.[*].edificio").value(hasItem(DEFAULT_EDIFICIO)))
            .andExpect(jsonPath("$.[*].fechaAntiguedad").value(hasItem(DEFAULT_FECHA_ANTIGUEDAD)))
            .andExpect(jsonPath("$.[*].codigoPostal").value(hasItem(DEFAULT_CODIGO_POSTAL)));
    }

    @Test
    @Transactional
    void getDomicilio() throws Exception {
        // Initialize the database
        domicilioRepository.saveAndFlush(domicilio);

        // Get the domicilio
        restDomicilioMockMvc
            .perform(get(ENTITY_API_URL_ID, domicilio.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(domicilio.getId().intValue()))
            .andExpect(jsonPath("$.calle").value(DEFAULT_CALLE))
            .andExpect(jsonPath("$.numeroExterior").value(DEFAULT_NUMERO_EXTERIOR))
            .andExpect(jsonPath("$.numeroInterior").value(DEFAULT_NUMERO_INTERIOR))
            .andExpect(jsonPath("$.codePostal").value(DEFAULT_CODE_POSTAL))
            .andExpect(jsonPath("$.domicilioActual").value(DEFAULT_DOMICILIO_ACTUAL))
            .andExpect(jsonPath("$.manzana").value(DEFAULT_MANZANA))
            .andExpect(jsonPath("$.andador").value(DEFAULT_ANDADOR))
            .andExpect(jsonPath("$.edificio").value(DEFAULT_EDIFICIO))
            .andExpect(jsonPath("$.fechaAntiguedad").value(DEFAULT_FECHA_ANTIGUEDAD))
            .andExpect(jsonPath("$.codigoPostal").value(DEFAULT_CODIGO_POSTAL));
    }

    @Test
    @Transactional
    void getNonExistingDomicilio() throws Exception {
        // Get the domicilio
        restDomicilioMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDomicilio() throws Exception {
        // Initialize the database
        domicilioRepository.saveAndFlush(domicilio);

        int databaseSizeBeforeUpdate = domicilioRepository.findAll().size();

        // Update the domicilio
        Domicilio updatedDomicilio = domicilioRepository.findById(domicilio.getId()).get();
        // Disconnect from session so that the updates on updatedDomicilio are not directly saved in db
        em.detach(updatedDomicilio);
        updatedDomicilio
            .calle(UPDATED_CALLE)
            .numeroExterior(UPDATED_NUMERO_EXTERIOR)
            .numeroInterior(UPDATED_NUMERO_INTERIOR)
            .codePostal(UPDATED_CODE_POSTAL)
            .domicilioActual(UPDATED_DOMICILIO_ACTUAL)
            .manzana(UPDATED_MANZANA)
            .andador(UPDATED_ANDADOR)
            .edificio(UPDATED_EDIFICIO)
            .fechaAntiguedad(UPDATED_FECHA_ANTIGUEDAD)
            .codigoPostal(UPDATED_CODIGO_POSTAL);

        restDomicilioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDomicilio.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedDomicilio))
            )
            .andExpect(status().isOk());

        // Validate the Domicilio in the database
        List<Domicilio> domicilioList = domicilioRepository.findAll();
        assertThat(domicilioList).hasSize(databaseSizeBeforeUpdate);
        Domicilio testDomicilio = domicilioList.get(domicilioList.size() - 1);
        assertThat(testDomicilio.getCalle()).isEqualTo(UPDATED_CALLE);
        assertThat(testDomicilio.getNumeroExterior()).isEqualTo(UPDATED_NUMERO_EXTERIOR);
        assertThat(testDomicilio.getNumeroInterior()).isEqualTo(UPDATED_NUMERO_INTERIOR);
        assertThat(testDomicilio.getCodePostal()).isEqualTo(UPDATED_CODE_POSTAL);
        assertThat(testDomicilio.getDomicilioActual()).isEqualTo(UPDATED_DOMICILIO_ACTUAL);
        assertThat(testDomicilio.getManzana()).isEqualTo(UPDATED_MANZANA);
        assertThat(testDomicilio.getAndador()).isEqualTo(UPDATED_ANDADOR);
        assertThat(testDomicilio.getEdificio()).isEqualTo(UPDATED_EDIFICIO);
        assertThat(testDomicilio.getFechaAntiguedad()).isEqualTo(UPDATED_FECHA_ANTIGUEDAD);
        assertThat(testDomicilio.getCodigoPostal()).isEqualTo(UPDATED_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    void putNonExistingDomicilio() throws Exception {
        int databaseSizeBeforeUpdate = domicilioRepository.findAll().size();
        domicilio.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDomicilioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, domicilio.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(domicilio))
            )
            .andExpect(status().isBadRequest());

        // Validate the Domicilio in the database
        List<Domicilio> domicilioList = domicilioRepository.findAll();
        assertThat(domicilioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDomicilio() throws Exception {
        int databaseSizeBeforeUpdate = domicilioRepository.findAll().size();
        domicilio.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDomicilioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(domicilio))
            )
            .andExpect(status().isBadRequest());

        // Validate the Domicilio in the database
        List<Domicilio> domicilioList = domicilioRepository.findAll();
        assertThat(domicilioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDomicilio() throws Exception {
        int databaseSizeBeforeUpdate = domicilioRepository.findAll().size();
        domicilio.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDomicilioMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(domicilio)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Domicilio in the database
        List<Domicilio> domicilioList = domicilioRepository.findAll();
        assertThat(domicilioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDomicilioWithPatch() throws Exception {
        // Initialize the database
        domicilioRepository.saveAndFlush(domicilio);

        int databaseSizeBeforeUpdate = domicilioRepository.findAll().size();

        // Update the domicilio using partial update
        Domicilio partialUpdatedDomicilio = new Domicilio();
        partialUpdatedDomicilio.setId(domicilio.getId());

        partialUpdatedDomicilio
            .calle(UPDATED_CALLE)
            .numeroInterior(UPDATED_NUMERO_INTERIOR)
            .domicilioActual(UPDATED_DOMICILIO_ACTUAL)
            .manzana(UPDATED_MANZANA)
            .fechaAntiguedad(UPDATED_FECHA_ANTIGUEDAD);

        restDomicilioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDomicilio.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDomicilio))
            )
            .andExpect(status().isOk());

        // Validate the Domicilio in the database
        List<Domicilio> domicilioList = domicilioRepository.findAll();
        assertThat(domicilioList).hasSize(databaseSizeBeforeUpdate);
        Domicilio testDomicilio = domicilioList.get(domicilioList.size() - 1);
        assertThat(testDomicilio.getCalle()).isEqualTo(UPDATED_CALLE);
        assertThat(testDomicilio.getNumeroExterior()).isEqualTo(DEFAULT_NUMERO_EXTERIOR);
        assertThat(testDomicilio.getNumeroInterior()).isEqualTo(UPDATED_NUMERO_INTERIOR);
        assertThat(testDomicilio.getCodePostal()).isEqualTo(DEFAULT_CODE_POSTAL);
        assertThat(testDomicilio.getDomicilioActual()).isEqualTo(UPDATED_DOMICILIO_ACTUAL);
        assertThat(testDomicilio.getManzana()).isEqualTo(UPDATED_MANZANA);
        assertThat(testDomicilio.getAndador()).isEqualTo(DEFAULT_ANDADOR);
        assertThat(testDomicilio.getEdificio()).isEqualTo(DEFAULT_EDIFICIO);
        assertThat(testDomicilio.getFechaAntiguedad()).isEqualTo(UPDATED_FECHA_ANTIGUEDAD);
        assertThat(testDomicilio.getCodigoPostal()).isEqualTo(DEFAULT_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    void fullUpdateDomicilioWithPatch() throws Exception {
        // Initialize the database
        domicilioRepository.saveAndFlush(domicilio);

        int databaseSizeBeforeUpdate = domicilioRepository.findAll().size();

        // Update the domicilio using partial update
        Domicilio partialUpdatedDomicilio = new Domicilio();
        partialUpdatedDomicilio.setId(domicilio.getId());

        partialUpdatedDomicilio
            .calle(UPDATED_CALLE)
            .numeroExterior(UPDATED_NUMERO_EXTERIOR)
            .numeroInterior(UPDATED_NUMERO_INTERIOR)
            .codePostal(UPDATED_CODE_POSTAL)
            .domicilioActual(UPDATED_DOMICILIO_ACTUAL)
            .manzana(UPDATED_MANZANA)
            .andador(UPDATED_ANDADOR)
            .edificio(UPDATED_EDIFICIO)
            .fechaAntiguedad(UPDATED_FECHA_ANTIGUEDAD)
            .codigoPostal(UPDATED_CODIGO_POSTAL);

        restDomicilioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDomicilio.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDomicilio))
            )
            .andExpect(status().isOk());

        // Validate the Domicilio in the database
        List<Domicilio> domicilioList = domicilioRepository.findAll();
        assertThat(domicilioList).hasSize(databaseSizeBeforeUpdate);
        Domicilio testDomicilio = domicilioList.get(domicilioList.size() - 1);
        assertThat(testDomicilio.getCalle()).isEqualTo(UPDATED_CALLE);
        assertThat(testDomicilio.getNumeroExterior()).isEqualTo(UPDATED_NUMERO_EXTERIOR);
        assertThat(testDomicilio.getNumeroInterior()).isEqualTo(UPDATED_NUMERO_INTERIOR);
        assertThat(testDomicilio.getCodePostal()).isEqualTo(UPDATED_CODE_POSTAL);
        assertThat(testDomicilio.getDomicilioActual()).isEqualTo(UPDATED_DOMICILIO_ACTUAL);
        assertThat(testDomicilio.getManzana()).isEqualTo(UPDATED_MANZANA);
        assertThat(testDomicilio.getAndador()).isEqualTo(UPDATED_ANDADOR);
        assertThat(testDomicilio.getEdificio()).isEqualTo(UPDATED_EDIFICIO);
        assertThat(testDomicilio.getFechaAntiguedad()).isEqualTo(UPDATED_FECHA_ANTIGUEDAD);
        assertThat(testDomicilio.getCodigoPostal()).isEqualTo(UPDATED_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    void patchNonExistingDomicilio() throws Exception {
        int databaseSizeBeforeUpdate = domicilioRepository.findAll().size();
        domicilio.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDomicilioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, domicilio.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(domicilio))
            )
            .andExpect(status().isBadRequest());

        // Validate the Domicilio in the database
        List<Domicilio> domicilioList = domicilioRepository.findAll();
        assertThat(domicilioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDomicilio() throws Exception {
        int databaseSizeBeforeUpdate = domicilioRepository.findAll().size();
        domicilio.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDomicilioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(domicilio))
            )
            .andExpect(status().isBadRequest());

        // Validate the Domicilio in the database
        List<Domicilio> domicilioList = domicilioRepository.findAll();
        assertThat(domicilioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDomicilio() throws Exception {
        int databaseSizeBeforeUpdate = domicilioRepository.findAll().size();
        domicilio.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDomicilioMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(domicilio))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Domicilio in the database
        List<Domicilio> domicilioList = domicilioRepository.findAll();
        assertThat(domicilioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDomicilio() throws Exception {
        // Initialize the database
        domicilioRepository.saveAndFlush(domicilio);

        int databaseSizeBeforeDelete = domicilioRepository.findAll().size();

        // Delete the domicilio
        restDomicilioMockMvc
            .perform(delete(ENTITY_API_URL_ID, domicilio.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Domicilio> domicilioList = domicilioRepository.findAll();
        assertThat(domicilioList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
