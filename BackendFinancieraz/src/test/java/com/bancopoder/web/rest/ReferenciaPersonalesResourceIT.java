package com.bancopoder.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bancopoder.IntegrationTest;
import com.bancopoder.domain.ReferenciaPersonales;
import com.bancopoder.repository.ReferenciaPersonalesRepository;
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
 * Integration tests for the {@link ReferenciaPersonalesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ReferenciaPersonalesResourceIT {

    private static final String DEFAULT_ID_PERSONA = "AAAAAAAAAA";
    private static final String UPDATED_ID_PERSONA = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDO_PATERNO = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDO_PATERNO = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDO_MATERNO = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDO_MATERNO = "BBBBBBBBBB";

    private static final String DEFAULT_FECHA_NACIMIENTO = "AAAAAAAAAA";
    private static final String UPDATED_FECHA_NACIMIENTO = "BBBBBBBBBB";

    private static final String DEFAULT_CELULAR = "AAAAAAAAAA";
    private static final String UPDATED_CELULAR = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO = "BBBBBBBBBB";

    private static final String DEFAULT_COMENTARIOS = "AAAAAAAAAA";
    private static final String UPDATED_COMENTARIOS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/referencia-personales";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ReferenciaPersonalesRepository referenciaPersonalesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReferenciaPersonalesMockMvc;

    private ReferenciaPersonales referenciaPersonales;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReferenciaPersonales createEntity(EntityManager em) {
        ReferenciaPersonales referenciaPersonales = new ReferenciaPersonales()
            .idPersona(DEFAULT_ID_PERSONA)
            .nombre(DEFAULT_NOMBRE)
            .apellidoPaterno(DEFAULT_APELLIDO_PATERNO)
            .apellidoMaterno(DEFAULT_APELLIDO_MATERNO)
            .fechaNacimiento(DEFAULT_FECHA_NACIMIENTO)
            .celular(DEFAULT_CELULAR)
            .telefono(DEFAULT_TELEFONO)
            .comentarios(DEFAULT_COMENTARIOS);
        return referenciaPersonales;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReferenciaPersonales createUpdatedEntity(EntityManager em) {
        ReferenciaPersonales referenciaPersonales = new ReferenciaPersonales()
            .idPersona(UPDATED_ID_PERSONA)
            .nombre(UPDATED_NOMBRE)
            .apellidoPaterno(UPDATED_APELLIDO_PATERNO)
            .apellidoMaterno(UPDATED_APELLIDO_MATERNO)
            .fechaNacimiento(UPDATED_FECHA_NACIMIENTO)
            .celular(UPDATED_CELULAR)
            .telefono(UPDATED_TELEFONO)
            .comentarios(UPDATED_COMENTARIOS);
        return referenciaPersonales;
    }

    @BeforeEach
    public void initTest() {
        referenciaPersonales = createEntity(em);
    }

    @Test
    @Transactional
    void createReferenciaPersonales() throws Exception {
        int databaseSizeBeforeCreate = referenciaPersonalesRepository.findAll().size();
        // Create the ReferenciaPersonales
        restReferenciaPersonalesMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(referenciaPersonales))
            )
            .andExpect(status().isCreated());

        // Validate the ReferenciaPersonales in the database
        List<ReferenciaPersonales> referenciaPersonalesList = referenciaPersonalesRepository.findAll();
        assertThat(referenciaPersonalesList).hasSize(databaseSizeBeforeCreate + 1);
        ReferenciaPersonales testReferenciaPersonales = referenciaPersonalesList.get(referenciaPersonalesList.size() - 1);
        assertThat(testReferenciaPersonales.getIdPersona()).isEqualTo(DEFAULT_ID_PERSONA);
        assertThat(testReferenciaPersonales.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testReferenciaPersonales.getApellidoPaterno()).isEqualTo(DEFAULT_APELLIDO_PATERNO);
        assertThat(testReferenciaPersonales.getApellidoMaterno()).isEqualTo(DEFAULT_APELLIDO_MATERNO);
        assertThat(testReferenciaPersonales.getFechaNacimiento()).isEqualTo(DEFAULT_FECHA_NACIMIENTO);
        assertThat(testReferenciaPersonales.getCelular()).isEqualTo(DEFAULT_CELULAR);
        assertThat(testReferenciaPersonales.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
        assertThat(testReferenciaPersonales.getComentarios()).isEqualTo(DEFAULT_COMENTARIOS);
    }

    @Test
    @Transactional
    void createReferenciaPersonalesWithExistingId() throws Exception {
        // Create the ReferenciaPersonales with an existing ID
        referenciaPersonales.setId(1L);

        int databaseSizeBeforeCreate = referenciaPersonalesRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restReferenciaPersonalesMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(referenciaPersonales))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReferenciaPersonales in the database
        List<ReferenciaPersonales> referenciaPersonalesList = referenciaPersonalesRepository.findAll();
        assertThat(referenciaPersonalesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllReferenciaPersonales() throws Exception {
        // Initialize the database
        referenciaPersonalesRepository.saveAndFlush(referenciaPersonales);

        // Get all the referenciaPersonalesList
        restReferenciaPersonalesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(referenciaPersonales.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPersona").value(hasItem(DEFAULT_ID_PERSONA)))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].apellidoPaterno").value(hasItem(DEFAULT_APELLIDO_PATERNO)))
            .andExpect(jsonPath("$.[*].apellidoMaterno").value(hasItem(DEFAULT_APELLIDO_MATERNO)))
            .andExpect(jsonPath("$.[*].fechaNacimiento").value(hasItem(DEFAULT_FECHA_NACIMIENTO)))
            .andExpect(jsonPath("$.[*].celular").value(hasItem(DEFAULT_CELULAR)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].comentarios").value(hasItem(DEFAULT_COMENTARIOS)));
    }

    @Test
    @Transactional
    void getReferenciaPersonales() throws Exception {
        // Initialize the database
        referenciaPersonalesRepository.saveAndFlush(referenciaPersonales);

        // Get the referenciaPersonales
        restReferenciaPersonalesMockMvc
            .perform(get(ENTITY_API_URL_ID, referenciaPersonales.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(referenciaPersonales.getId().intValue()))
            .andExpect(jsonPath("$.idPersona").value(DEFAULT_ID_PERSONA))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.apellidoPaterno").value(DEFAULT_APELLIDO_PATERNO))
            .andExpect(jsonPath("$.apellidoMaterno").value(DEFAULT_APELLIDO_MATERNO))
            .andExpect(jsonPath("$.fechaNacimiento").value(DEFAULT_FECHA_NACIMIENTO))
            .andExpect(jsonPath("$.celular").value(DEFAULT_CELULAR))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO))
            .andExpect(jsonPath("$.comentarios").value(DEFAULT_COMENTARIOS));
    }

    @Test
    @Transactional
    void getNonExistingReferenciaPersonales() throws Exception {
        // Get the referenciaPersonales
        restReferenciaPersonalesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewReferenciaPersonales() throws Exception {
        // Initialize the database
        referenciaPersonalesRepository.saveAndFlush(referenciaPersonales);

        int databaseSizeBeforeUpdate = referenciaPersonalesRepository.findAll().size();

        // Update the referenciaPersonales
        ReferenciaPersonales updatedReferenciaPersonales = referenciaPersonalesRepository.findById(referenciaPersonales.getId()).get();
        // Disconnect from session so that the updates on updatedReferenciaPersonales are not directly saved in db
        em.detach(updatedReferenciaPersonales);
        updatedReferenciaPersonales
            .idPersona(UPDATED_ID_PERSONA)
            .nombre(UPDATED_NOMBRE)
            .apellidoPaterno(UPDATED_APELLIDO_PATERNO)
            .apellidoMaterno(UPDATED_APELLIDO_MATERNO)
            .fechaNacimiento(UPDATED_FECHA_NACIMIENTO)
            .celular(UPDATED_CELULAR)
            .telefono(UPDATED_TELEFONO)
            .comentarios(UPDATED_COMENTARIOS);

        restReferenciaPersonalesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedReferenciaPersonales.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedReferenciaPersonales))
            )
            .andExpect(status().isOk());

        // Validate the ReferenciaPersonales in the database
        List<ReferenciaPersonales> referenciaPersonalesList = referenciaPersonalesRepository.findAll();
        assertThat(referenciaPersonalesList).hasSize(databaseSizeBeforeUpdate);
        ReferenciaPersonales testReferenciaPersonales = referenciaPersonalesList.get(referenciaPersonalesList.size() - 1);
        assertThat(testReferenciaPersonales.getIdPersona()).isEqualTo(UPDATED_ID_PERSONA);
        assertThat(testReferenciaPersonales.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testReferenciaPersonales.getApellidoPaterno()).isEqualTo(UPDATED_APELLIDO_PATERNO);
        assertThat(testReferenciaPersonales.getApellidoMaterno()).isEqualTo(UPDATED_APELLIDO_MATERNO);
        assertThat(testReferenciaPersonales.getFechaNacimiento()).isEqualTo(UPDATED_FECHA_NACIMIENTO);
        assertThat(testReferenciaPersonales.getCelular()).isEqualTo(UPDATED_CELULAR);
        assertThat(testReferenciaPersonales.getTelefono()).isEqualTo(UPDATED_TELEFONO);
        assertThat(testReferenciaPersonales.getComentarios()).isEqualTo(UPDATED_COMENTARIOS);
    }

    @Test
    @Transactional
    void putNonExistingReferenciaPersonales() throws Exception {
        int databaseSizeBeforeUpdate = referenciaPersonalesRepository.findAll().size();
        referenciaPersonales.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReferenciaPersonalesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, referenciaPersonales.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(referenciaPersonales))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReferenciaPersonales in the database
        List<ReferenciaPersonales> referenciaPersonalesList = referenciaPersonalesRepository.findAll();
        assertThat(referenciaPersonalesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchReferenciaPersonales() throws Exception {
        int databaseSizeBeforeUpdate = referenciaPersonalesRepository.findAll().size();
        referenciaPersonales.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReferenciaPersonalesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(referenciaPersonales))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReferenciaPersonales in the database
        List<ReferenciaPersonales> referenciaPersonalesList = referenciaPersonalesRepository.findAll();
        assertThat(referenciaPersonalesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamReferenciaPersonales() throws Exception {
        int databaseSizeBeforeUpdate = referenciaPersonalesRepository.findAll().size();
        referenciaPersonales.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReferenciaPersonalesMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(referenciaPersonales))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ReferenciaPersonales in the database
        List<ReferenciaPersonales> referenciaPersonalesList = referenciaPersonalesRepository.findAll();
        assertThat(referenciaPersonalesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateReferenciaPersonalesWithPatch() throws Exception {
        // Initialize the database
        referenciaPersonalesRepository.saveAndFlush(referenciaPersonales);

        int databaseSizeBeforeUpdate = referenciaPersonalesRepository.findAll().size();

        // Update the referenciaPersonales using partial update
        ReferenciaPersonales partialUpdatedReferenciaPersonales = new ReferenciaPersonales();
        partialUpdatedReferenciaPersonales.setId(referenciaPersonales.getId());

        partialUpdatedReferenciaPersonales.fechaNacimiento(UPDATED_FECHA_NACIMIENTO).celular(UPDATED_CELULAR);

        restReferenciaPersonalesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReferenciaPersonales.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReferenciaPersonales))
            )
            .andExpect(status().isOk());

        // Validate the ReferenciaPersonales in the database
        List<ReferenciaPersonales> referenciaPersonalesList = referenciaPersonalesRepository.findAll();
        assertThat(referenciaPersonalesList).hasSize(databaseSizeBeforeUpdate);
        ReferenciaPersonales testReferenciaPersonales = referenciaPersonalesList.get(referenciaPersonalesList.size() - 1);
        assertThat(testReferenciaPersonales.getIdPersona()).isEqualTo(DEFAULT_ID_PERSONA);
        assertThat(testReferenciaPersonales.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testReferenciaPersonales.getApellidoPaterno()).isEqualTo(DEFAULT_APELLIDO_PATERNO);
        assertThat(testReferenciaPersonales.getApellidoMaterno()).isEqualTo(DEFAULT_APELLIDO_MATERNO);
        assertThat(testReferenciaPersonales.getFechaNacimiento()).isEqualTo(UPDATED_FECHA_NACIMIENTO);
        assertThat(testReferenciaPersonales.getCelular()).isEqualTo(UPDATED_CELULAR);
        assertThat(testReferenciaPersonales.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
        assertThat(testReferenciaPersonales.getComentarios()).isEqualTo(DEFAULT_COMENTARIOS);
    }

    @Test
    @Transactional
    void fullUpdateReferenciaPersonalesWithPatch() throws Exception {
        // Initialize the database
        referenciaPersonalesRepository.saveAndFlush(referenciaPersonales);

        int databaseSizeBeforeUpdate = referenciaPersonalesRepository.findAll().size();

        // Update the referenciaPersonales using partial update
        ReferenciaPersonales partialUpdatedReferenciaPersonales = new ReferenciaPersonales();
        partialUpdatedReferenciaPersonales.setId(referenciaPersonales.getId());

        partialUpdatedReferenciaPersonales
            .idPersona(UPDATED_ID_PERSONA)
            .nombre(UPDATED_NOMBRE)
            .apellidoPaterno(UPDATED_APELLIDO_PATERNO)
            .apellidoMaterno(UPDATED_APELLIDO_MATERNO)
            .fechaNacimiento(UPDATED_FECHA_NACIMIENTO)
            .celular(UPDATED_CELULAR)
            .telefono(UPDATED_TELEFONO)
            .comentarios(UPDATED_COMENTARIOS);

        restReferenciaPersonalesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReferenciaPersonales.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReferenciaPersonales))
            )
            .andExpect(status().isOk());

        // Validate the ReferenciaPersonales in the database
        List<ReferenciaPersonales> referenciaPersonalesList = referenciaPersonalesRepository.findAll();
        assertThat(referenciaPersonalesList).hasSize(databaseSizeBeforeUpdate);
        ReferenciaPersonales testReferenciaPersonales = referenciaPersonalesList.get(referenciaPersonalesList.size() - 1);
        assertThat(testReferenciaPersonales.getIdPersona()).isEqualTo(UPDATED_ID_PERSONA);
        assertThat(testReferenciaPersonales.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testReferenciaPersonales.getApellidoPaterno()).isEqualTo(UPDATED_APELLIDO_PATERNO);
        assertThat(testReferenciaPersonales.getApellidoMaterno()).isEqualTo(UPDATED_APELLIDO_MATERNO);
        assertThat(testReferenciaPersonales.getFechaNacimiento()).isEqualTo(UPDATED_FECHA_NACIMIENTO);
        assertThat(testReferenciaPersonales.getCelular()).isEqualTo(UPDATED_CELULAR);
        assertThat(testReferenciaPersonales.getTelefono()).isEqualTo(UPDATED_TELEFONO);
        assertThat(testReferenciaPersonales.getComentarios()).isEqualTo(UPDATED_COMENTARIOS);
    }

    @Test
    @Transactional
    void patchNonExistingReferenciaPersonales() throws Exception {
        int databaseSizeBeforeUpdate = referenciaPersonalesRepository.findAll().size();
        referenciaPersonales.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReferenciaPersonalesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, referenciaPersonales.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(referenciaPersonales))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReferenciaPersonales in the database
        List<ReferenciaPersonales> referenciaPersonalesList = referenciaPersonalesRepository.findAll();
        assertThat(referenciaPersonalesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchReferenciaPersonales() throws Exception {
        int databaseSizeBeforeUpdate = referenciaPersonalesRepository.findAll().size();
        referenciaPersonales.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReferenciaPersonalesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(referenciaPersonales))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReferenciaPersonales in the database
        List<ReferenciaPersonales> referenciaPersonalesList = referenciaPersonalesRepository.findAll();
        assertThat(referenciaPersonalesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamReferenciaPersonales() throws Exception {
        int databaseSizeBeforeUpdate = referenciaPersonalesRepository.findAll().size();
        referenciaPersonales.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReferenciaPersonalesMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(referenciaPersonales))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ReferenciaPersonales in the database
        List<ReferenciaPersonales> referenciaPersonalesList = referenciaPersonalesRepository.findAll();
        assertThat(referenciaPersonalesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteReferenciaPersonales() throws Exception {
        // Initialize the database
        referenciaPersonalesRepository.saveAndFlush(referenciaPersonales);

        int databaseSizeBeforeDelete = referenciaPersonalesRepository.findAll().size();

        // Delete the referenciaPersonales
        restReferenciaPersonalesMockMvc
            .perform(delete(ENTITY_API_URL_ID, referenciaPersonales.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ReferenciaPersonales> referenciaPersonalesList = referenciaPersonalesRepository.findAll();
        assertThat(referenciaPersonalesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
