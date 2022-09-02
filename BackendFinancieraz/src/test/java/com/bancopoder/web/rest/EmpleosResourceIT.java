package com.bancopoder.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bancopoder.IntegrationTest;
import com.bancopoder.domain.Empleos;
import com.bancopoder.repository.EmpleosRepository;
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
 * Integration tests for the {@link EmpleosResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EmpleosResourceIT {

    private static final String DEFAULT_INGRESOS_COMPROBABLES = "AAAAAAAAAA";
    private static final String UPDATED_INGRESOS_COMPROBABLES = "BBBBBBBBBB";

    private static final String DEFAULT_CARGO_PUBLICO = "AAAAAAAAAA";
    private static final String UPDATED_CARGO_PUBLICO = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILIAR_CARGO_PUBLICO = "AAAAAAAAAA";
    private static final String UPDATED_FAMILIAR_CARGO_PUBLICO = "BBBBBBBBBB";

    private static final String DEFAULT_CALLE = "AAAAAAAAAA";
    private static final String UPDATED_CALLE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_EXTERIOR = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_EXTERIOR = "BBBBBBBBBB";

    private static final String DEFAULT_CODIGO_POSTAL = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO_POSTAL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/empleos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EmpleosRepository empleosRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEmpleosMockMvc;

    private Empleos empleos;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Empleos createEntity(EntityManager em) {
        Empleos empleos = new Empleos()
            .ingresosComprobables(DEFAULT_INGRESOS_COMPROBABLES)
            .cargoPublico(DEFAULT_CARGO_PUBLICO)
            .familiarCargoPublico(DEFAULT_FAMILIAR_CARGO_PUBLICO)
            .calle(DEFAULT_CALLE)
            .numeroExterior(DEFAULT_NUMERO_EXTERIOR)
            .codigoPostal(DEFAULT_CODIGO_POSTAL);
        return empleos;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Empleos createUpdatedEntity(EntityManager em) {
        Empleos empleos = new Empleos()
            .ingresosComprobables(UPDATED_INGRESOS_COMPROBABLES)
            .cargoPublico(UPDATED_CARGO_PUBLICO)
            .familiarCargoPublico(UPDATED_FAMILIAR_CARGO_PUBLICO)
            .calle(UPDATED_CALLE)
            .numeroExterior(UPDATED_NUMERO_EXTERIOR)
            .codigoPostal(UPDATED_CODIGO_POSTAL);
        return empleos;
    }

    @BeforeEach
    public void initTest() {
        empleos = createEntity(em);
    }

    @Test
    @Transactional
    void createEmpleos() throws Exception {
        int databaseSizeBeforeCreate = empleosRepository.findAll().size();
        // Create the Empleos
        restEmpleosMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleos)))
            .andExpect(status().isCreated());

        // Validate the Empleos in the database
        List<Empleos> empleosList = empleosRepository.findAll();
        assertThat(empleosList).hasSize(databaseSizeBeforeCreate + 1);
        Empleos testEmpleos = empleosList.get(empleosList.size() - 1);
        assertThat(testEmpleos.getIngresosComprobables()).isEqualTo(DEFAULT_INGRESOS_COMPROBABLES);
        assertThat(testEmpleos.getCargoPublico()).isEqualTo(DEFAULT_CARGO_PUBLICO);
        assertThat(testEmpleos.getFamiliarCargoPublico()).isEqualTo(DEFAULT_FAMILIAR_CARGO_PUBLICO);
        assertThat(testEmpleos.getCalle()).isEqualTo(DEFAULT_CALLE);
        assertThat(testEmpleos.getNumeroExterior()).isEqualTo(DEFAULT_NUMERO_EXTERIOR);
        assertThat(testEmpleos.getCodigoPostal()).isEqualTo(DEFAULT_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    void createEmpleosWithExistingId() throws Exception {
        // Create the Empleos with an existing ID
        empleos.setId(1L);

        int databaseSizeBeforeCreate = empleosRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmpleosMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleos)))
            .andExpect(status().isBadRequest());

        // Validate the Empleos in the database
        List<Empleos> empleosList = empleosRepository.findAll();
        assertThat(empleosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEmpleos() throws Exception {
        // Initialize the database
        empleosRepository.saveAndFlush(empleos);

        // Get all the empleosList
        restEmpleosMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(empleos.getId().intValue())))
            .andExpect(jsonPath("$.[*].ingresosComprobables").value(hasItem(DEFAULT_INGRESOS_COMPROBABLES)))
            .andExpect(jsonPath("$.[*].cargoPublico").value(hasItem(DEFAULT_CARGO_PUBLICO)))
            .andExpect(jsonPath("$.[*].familiarCargoPublico").value(hasItem(DEFAULT_FAMILIAR_CARGO_PUBLICO)))
            .andExpect(jsonPath("$.[*].calle").value(hasItem(DEFAULT_CALLE)))
            .andExpect(jsonPath("$.[*].numeroExterior").value(hasItem(DEFAULT_NUMERO_EXTERIOR)))
            .andExpect(jsonPath("$.[*].codigoPostal").value(hasItem(DEFAULT_CODIGO_POSTAL)));
    }

    @Test
    @Transactional
    void getEmpleos() throws Exception {
        // Initialize the database
        empleosRepository.saveAndFlush(empleos);

        // Get the empleos
        restEmpleosMockMvc
            .perform(get(ENTITY_API_URL_ID, empleos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(empleos.getId().intValue()))
            .andExpect(jsonPath("$.ingresosComprobables").value(DEFAULT_INGRESOS_COMPROBABLES))
            .andExpect(jsonPath("$.cargoPublico").value(DEFAULT_CARGO_PUBLICO))
            .andExpect(jsonPath("$.familiarCargoPublico").value(DEFAULT_FAMILIAR_CARGO_PUBLICO))
            .andExpect(jsonPath("$.calle").value(DEFAULT_CALLE))
            .andExpect(jsonPath("$.numeroExterior").value(DEFAULT_NUMERO_EXTERIOR))
            .andExpect(jsonPath("$.codigoPostal").value(DEFAULT_CODIGO_POSTAL));
    }

    @Test
    @Transactional
    void getNonExistingEmpleos() throws Exception {
        // Get the empleos
        restEmpleosMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewEmpleos() throws Exception {
        // Initialize the database
        empleosRepository.saveAndFlush(empleos);

        int databaseSizeBeforeUpdate = empleosRepository.findAll().size();

        // Update the empleos
        Empleos updatedEmpleos = empleosRepository.findById(empleos.getId()).get();
        // Disconnect from session so that the updates on updatedEmpleos are not directly saved in db
        em.detach(updatedEmpleos);
        updatedEmpleos
            .ingresosComprobables(UPDATED_INGRESOS_COMPROBABLES)
            .cargoPublico(UPDATED_CARGO_PUBLICO)
            .familiarCargoPublico(UPDATED_FAMILIAR_CARGO_PUBLICO)
            .calle(UPDATED_CALLE)
            .numeroExterior(UPDATED_NUMERO_EXTERIOR)
            .codigoPostal(UPDATED_CODIGO_POSTAL);

        restEmpleosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEmpleos.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedEmpleos))
            )
            .andExpect(status().isOk());

        // Validate the Empleos in the database
        List<Empleos> empleosList = empleosRepository.findAll();
        assertThat(empleosList).hasSize(databaseSizeBeforeUpdate);
        Empleos testEmpleos = empleosList.get(empleosList.size() - 1);
        assertThat(testEmpleos.getIngresosComprobables()).isEqualTo(UPDATED_INGRESOS_COMPROBABLES);
        assertThat(testEmpleos.getCargoPublico()).isEqualTo(UPDATED_CARGO_PUBLICO);
        assertThat(testEmpleos.getFamiliarCargoPublico()).isEqualTo(UPDATED_FAMILIAR_CARGO_PUBLICO);
        assertThat(testEmpleos.getCalle()).isEqualTo(UPDATED_CALLE);
        assertThat(testEmpleos.getNumeroExterior()).isEqualTo(UPDATED_NUMERO_EXTERIOR);
        assertThat(testEmpleos.getCodigoPostal()).isEqualTo(UPDATED_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    void putNonExistingEmpleos() throws Exception {
        int databaseSizeBeforeUpdate = empleosRepository.findAll().size();
        empleos.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmpleosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, empleos.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(empleos))
            )
            .andExpect(status().isBadRequest());

        // Validate the Empleos in the database
        List<Empleos> empleosList = empleosRepository.findAll();
        assertThat(empleosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEmpleos() throws Exception {
        int databaseSizeBeforeUpdate = empleosRepository.findAll().size();
        empleos.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmpleosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(empleos))
            )
            .andExpect(status().isBadRequest());

        // Validate the Empleos in the database
        List<Empleos> empleosList = empleosRepository.findAll();
        assertThat(empleosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEmpleos() throws Exception {
        int databaseSizeBeforeUpdate = empleosRepository.findAll().size();
        empleos.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmpleosMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleos)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Empleos in the database
        List<Empleos> empleosList = empleosRepository.findAll();
        assertThat(empleosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEmpleosWithPatch() throws Exception {
        // Initialize the database
        empleosRepository.saveAndFlush(empleos);

        int databaseSizeBeforeUpdate = empleosRepository.findAll().size();

        // Update the empleos using partial update
        Empleos partialUpdatedEmpleos = new Empleos();
        partialUpdatedEmpleos.setId(empleos.getId());

        partialUpdatedEmpleos.ingresosComprobables(UPDATED_INGRESOS_COMPROBABLES).familiarCargoPublico(UPDATED_FAMILIAR_CARGO_PUBLICO);

        restEmpleosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEmpleos.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEmpleos))
            )
            .andExpect(status().isOk());

        // Validate the Empleos in the database
        List<Empleos> empleosList = empleosRepository.findAll();
        assertThat(empleosList).hasSize(databaseSizeBeforeUpdate);
        Empleos testEmpleos = empleosList.get(empleosList.size() - 1);
        assertThat(testEmpleos.getIngresosComprobables()).isEqualTo(UPDATED_INGRESOS_COMPROBABLES);
        assertThat(testEmpleos.getCargoPublico()).isEqualTo(DEFAULT_CARGO_PUBLICO);
        assertThat(testEmpleos.getFamiliarCargoPublico()).isEqualTo(UPDATED_FAMILIAR_CARGO_PUBLICO);
        assertThat(testEmpleos.getCalle()).isEqualTo(DEFAULT_CALLE);
        assertThat(testEmpleos.getNumeroExterior()).isEqualTo(DEFAULT_NUMERO_EXTERIOR);
        assertThat(testEmpleos.getCodigoPostal()).isEqualTo(DEFAULT_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    void fullUpdateEmpleosWithPatch() throws Exception {
        // Initialize the database
        empleosRepository.saveAndFlush(empleos);

        int databaseSizeBeforeUpdate = empleosRepository.findAll().size();

        // Update the empleos using partial update
        Empleos partialUpdatedEmpleos = new Empleos();
        partialUpdatedEmpleos.setId(empleos.getId());

        partialUpdatedEmpleos
            .ingresosComprobables(UPDATED_INGRESOS_COMPROBABLES)
            .cargoPublico(UPDATED_CARGO_PUBLICO)
            .familiarCargoPublico(UPDATED_FAMILIAR_CARGO_PUBLICO)
            .calle(UPDATED_CALLE)
            .numeroExterior(UPDATED_NUMERO_EXTERIOR)
            .codigoPostal(UPDATED_CODIGO_POSTAL);

        restEmpleosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEmpleos.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEmpleos))
            )
            .andExpect(status().isOk());

        // Validate the Empleos in the database
        List<Empleos> empleosList = empleosRepository.findAll();
        assertThat(empleosList).hasSize(databaseSizeBeforeUpdate);
        Empleos testEmpleos = empleosList.get(empleosList.size() - 1);
        assertThat(testEmpleos.getIngresosComprobables()).isEqualTo(UPDATED_INGRESOS_COMPROBABLES);
        assertThat(testEmpleos.getCargoPublico()).isEqualTo(UPDATED_CARGO_PUBLICO);
        assertThat(testEmpleos.getFamiliarCargoPublico()).isEqualTo(UPDATED_FAMILIAR_CARGO_PUBLICO);
        assertThat(testEmpleos.getCalle()).isEqualTo(UPDATED_CALLE);
        assertThat(testEmpleos.getNumeroExterior()).isEqualTo(UPDATED_NUMERO_EXTERIOR);
        assertThat(testEmpleos.getCodigoPostal()).isEqualTo(UPDATED_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    void patchNonExistingEmpleos() throws Exception {
        int databaseSizeBeforeUpdate = empleosRepository.findAll().size();
        empleos.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmpleosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, empleos.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(empleos))
            )
            .andExpect(status().isBadRequest());

        // Validate the Empleos in the database
        List<Empleos> empleosList = empleosRepository.findAll();
        assertThat(empleosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEmpleos() throws Exception {
        int databaseSizeBeforeUpdate = empleosRepository.findAll().size();
        empleos.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmpleosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(empleos))
            )
            .andExpect(status().isBadRequest());

        // Validate the Empleos in the database
        List<Empleos> empleosList = empleosRepository.findAll();
        assertThat(empleosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEmpleos() throws Exception {
        int databaseSizeBeforeUpdate = empleosRepository.findAll().size();
        empleos.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmpleosMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(empleos)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Empleos in the database
        List<Empleos> empleosList = empleosRepository.findAll();
        assertThat(empleosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEmpleos() throws Exception {
        // Initialize the database
        empleosRepository.saveAndFlush(empleos);

        int databaseSizeBeforeDelete = empleosRepository.findAll().size();

        // Delete the empleos
        restEmpleosMockMvc
            .perform(delete(ENTITY_API_URL_ID, empleos.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Empleos> empleosList = empleosRepository.findAll();
        assertThat(empleosList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
