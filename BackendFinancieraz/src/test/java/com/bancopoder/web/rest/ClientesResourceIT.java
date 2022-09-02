package com.bancopoder.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bancopoder.IntegrationTest;
import com.bancopoder.domain.Clientes;
import com.bancopoder.repository.ClientesRepository;
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
 * Integration tests for the {@link ClientesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClientesResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDO_PATERNO = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDO_PATERNO = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDO_MATERNO = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDO_MATERNO = "BBBBBBBBBB";

    private static final String DEFAULT_FECHA_NACIMIENTO = "AAAAAAAAAA";
    private static final String UPDATED_FECHA_NACIMIENTO = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIFICACION_OCR = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFICACION_OCR = "BBBBBBBBBB";

    private static final String DEFAULT_CLAVE_ELECTOR = "AAAAAAAAAA";
    private static final String UPDATED_CLAVE_ELECTOR = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO = "BBBBBBBBBB";

    private static final String DEFAULT_CELULAR = "AAAAAAAAAA";
    private static final String UPDATED_CELULAR = "BBBBBBBBBB";

    private static final String DEFAULT_CORREO_ELECTRONICO = "AAAAAAAAAA";
    private static final String UPDATED_CORREO_ELECTRONICO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/clientes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClientesMockMvc;

    private Clientes clientes;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Clientes createEntity(EntityManager em) {
        Clientes clientes = new Clientes()
            .nombre(DEFAULT_NOMBRE)
            .apellidoPaterno(DEFAULT_APELLIDO_PATERNO)
            .apellidoMaterno(DEFAULT_APELLIDO_MATERNO)
            .fechaNacimiento(DEFAULT_FECHA_NACIMIENTO)
            .identificacionOCR(DEFAULT_IDENTIFICACION_OCR)
            .claveElector(DEFAULT_CLAVE_ELECTOR)
            .telefono(DEFAULT_TELEFONO)
            .celular(DEFAULT_CELULAR)
            .correoElectronico(DEFAULT_CORREO_ELECTRONICO);
        return clientes;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Clientes createUpdatedEntity(EntityManager em) {
        Clientes clientes = new Clientes()
            .nombre(UPDATED_NOMBRE)
            .apellidoPaterno(UPDATED_APELLIDO_PATERNO)
            .apellidoMaterno(UPDATED_APELLIDO_MATERNO)
            .fechaNacimiento(UPDATED_FECHA_NACIMIENTO)
            .identificacionOCR(UPDATED_IDENTIFICACION_OCR)
            .claveElector(UPDATED_CLAVE_ELECTOR)
            .telefono(UPDATED_TELEFONO)
            .celular(UPDATED_CELULAR)
            .correoElectronico(UPDATED_CORREO_ELECTRONICO);
        return clientes;
    }

    @BeforeEach
    public void initTest() {
        clientes = createEntity(em);
    }

    @Test
    @Transactional
    void createClientes() throws Exception {
        int databaseSizeBeforeCreate = clientesRepository.findAll().size();
        // Create the Clientes
        restClientesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientes)))
            .andExpect(status().isCreated());

        // Validate the Clientes in the database
        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeCreate + 1);
        Clientes testClientes = clientesList.get(clientesList.size() - 1);
        assertThat(testClientes.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testClientes.getApellidoPaterno()).isEqualTo(DEFAULT_APELLIDO_PATERNO);
        assertThat(testClientes.getApellidoMaterno()).isEqualTo(DEFAULT_APELLIDO_MATERNO);
        assertThat(testClientes.getFechaNacimiento()).isEqualTo(DEFAULT_FECHA_NACIMIENTO);
        assertThat(testClientes.getIdentificacionOCR()).isEqualTo(DEFAULT_IDENTIFICACION_OCR);
        assertThat(testClientes.getClaveElector()).isEqualTo(DEFAULT_CLAVE_ELECTOR);
        assertThat(testClientes.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
        assertThat(testClientes.getCelular()).isEqualTo(DEFAULT_CELULAR);
        assertThat(testClientes.getCorreoElectronico()).isEqualTo(DEFAULT_CORREO_ELECTRONICO);
    }

    @Test
    @Transactional
    void createClientesWithExistingId() throws Exception {
        // Create the Clientes with an existing ID
        clientes.setId(1L);

        int databaseSizeBeforeCreate = clientesRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClientesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientes)))
            .andExpect(status().isBadRequest());

        // Validate the Clientes in the database
        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllClientes() throws Exception {
        // Initialize the database
        clientesRepository.saveAndFlush(clientes);

        // Get all the clientesList
        restClientesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(clientes.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].apellidoPaterno").value(hasItem(DEFAULT_APELLIDO_PATERNO)))
            .andExpect(jsonPath("$.[*].apellidoMaterno").value(hasItem(DEFAULT_APELLIDO_MATERNO)))
            .andExpect(jsonPath("$.[*].fechaNacimiento").value(hasItem(DEFAULT_FECHA_NACIMIENTO)))
            .andExpect(jsonPath("$.[*].identificacionOCR").value(hasItem(DEFAULT_IDENTIFICACION_OCR)))
            .andExpect(jsonPath("$.[*].claveElector").value(hasItem(DEFAULT_CLAVE_ELECTOR)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].celular").value(hasItem(DEFAULT_CELULAR)))
            .andExpect(jsonPath("$.[*].correoElectronico").value(hasItem(DEFAULT_CORREO_ELECTRONICO)));
    }

    @Test
    @Transactional
    void getClientes() throws Exception {
        // Initialize the database
        clientesRepository.saveAndFlush(clientes);

        // Get the clientes
        restClientesMockMvc
            .perform(get(ENTITY_API_URL_ID, clientes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(clientes.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.apellidoPaterno").value(DEFAULT_APELLIDO_PATERNO))
            .andExpect(jsonPath("$.apellidoMaterno").value(DEFAULT_APELLIDO_MATERNO))
            .andExpect(jsonPath("$.fechaNacimiento").value(DEFAULT_FECHA_NACIMIENTO))
            .andExpect(jsonPath("$.identificacionOCR").value(DEFAULT_IDENTIFICACION_OCR))
            .andExpect(jsonPath("$.claveElector").value(DEFAULT_CLAVE_ELECTOR))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO))
            .andExpect(jsonPath("$.celular").value(DEFAULT_CELULAR))
            .andExpect(jsonPath("$.correoElectronico").value(DEFAULT_CORREO_ELECTRONICO));
    }

    @Test
    @Transactional
    void getNonExistingClientes() throws Exception {
        // Get the clientes
        restClientesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewClientes() throws Exception {
        // Initialize the database
        clientesRepository.saveAndFlush(clientes);

        int databaseSizeBeforeUpdate = clientesRepository.findAll().size();

        // Update the clientes
        Clientes updatedClientes = clientesRepository.findById(clientes.getId()).get();
        // Disconnect from session so that the updates on updatedClientes are not directly saved in db
        em.detach(updatedClientes);
        updatedClientes
            .nombre(UPDATED_NOMBRE)
            .apellidoPaterno(UPDATED_APELLIDO_PATERNO)
            .apellidoMaterno(UPDATED_APELLIDO_MATERNO)
            .fechaNacimiento(UPDATED_FECHA_NACIMIENTO)
            .identificacionOCR(UPDATED_IDENTIFICACION_OCR)
            .claveElector(UPDATED_CLAVE_ELECTOR)
            .telefono(UPDATED_TELEFONO)
            .celular(UPDATED_CELULAR)
            .correoElectronico(UPDATED_CORREO_ELECTRONICO);

        restClientesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedClientes.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedClientes))
            )
            .andExpect(status().isOk());

        // Validate the Clientes in the database
        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeUpdate);
        Clientes testClientes = clientesList.get(clientesList.size() - 1);
        assertThat(testClientes.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testClientes.getApellidoPaterno()).isEqualTo(UPDATED_APELLIDO_PATERNO);
        assertThat(testClientes.getApellidoMaterno()).isEqualTo(UPDATED_APELLIDO_MATERNO);
        assertThat(testClientes.getFechaNacimiento()).isEqualTo(UPDATED_FECHA_NACIMIENTO);
        assertThat(testClientes.getIdentificacionOCR()).isEqualTo(UPDATED_IDENTIFICACION_OCR);
        assertThat(testClientes.getClaveElector()).isEqualTo(UPDATED_CLAVE_ELECTOR);
        assertThat(testClientes.getTelefono()).isEqualTo(UPDATED_TELEFONO);
        assertThat(testClientes.getCelular()).isEqualTo(UPDATED_CELULAR);
        assertThat(testClientes.getCorreoElectronico()).isEqualTo(UPDATED_CORREO_ELECTRONICO);
    }

    @Test
    @Transactional
    void putNonExistingClientes() throws Exception {
        int databaseSizeBeforeUpdate = clientesRepository.findAll().size();
        clientes.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, clientes.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientes))
            )
            .andExpect(status().isBadRequest());

        // Validate the Clientes in the database
        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchClientes() throws Exception {
        int databaseSizeBeforeUpdate = clientesRepository.findAll().size();
        clientes.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientes))
            )
            .andExpect(status().isBadRequest());

        // Validate the Clientes in the database
        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamClientes() throws Exception {
        int databaseSizeBeforeUpdate = clientesRepository.findAll().size();
        clientes.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientes)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Clientes in the database
        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateClientesWithPatch() throws Exception {
        // Initialize the database
        clientesRepository.saveAndFlush(clientes);

        int databaseSizeBeforeUpdate = clientesRepository.findAll().size();

        // Update the clientes using partial update
        Clientes partialUpdatedClientes = new Clientes();
        partialUpdatedClientes.setId(clientes.getId());

        partialUpdatedClientes
            .apellidoPaterno(UPDATED_APELLIDO_PATERNO)
            .apellidoMaterno(UPDATED_APELLIDO_MATERNO)
            .claveElector(UPDATED_CLAVE_ELECTOR)
            .telefono(UPDATED_TELEFONO)
            .celular(UPDATED_CELULAR)
            .correoElectronico(UPDATED_CORREO_ELECTRONICO);

        restClientesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClientes.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClientes))
            )
            .andExpect(status().isOk());

        // Validate the Clientes in the database
        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeUpdate);
        Clientes testClientes = clientesList.get(clientesList.size() - 1);
        assertThat(testClientes.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testClientes.getApellidoPaterno()).isEqualTo(UPDATED_APELLIDO_PATERNO);
        assertThat(testClientes.getApellidoMaterno()).isEqualTo(UPDATED_APELLIDO_MATERNO);
        assertThat(testClientes.getFechaNacimiento()).isEqualTo(DEFAULT_FECHA_NACIMIENTO);
        assertThat(testClientes.getIdentificacionOCR()).isEqualTo(DEFAULT_IDENTIFICACION_OCR);
        assertThat(testClientes.getClaveElector()).isEqualTo(UPDATED_CLAVE_ELECTOR);
        assertThat(testClientes.getTelefono()).isEqualTo(UPDATED_TELEFONO);
        assertThat(testClientes.getCelular()).isEqualTo(UPDATED_CELULAR);
        assertThat(testClientes.getCorreoElectronico()).isEqualTo(UPDATED_CORREO_ELECTRONICO);
    }

    @Test
    @Transactional
    void fullUpdateClientesWithPatch() throws Exception {
        // Initialize the database
        clientesRepository.saveAndFlush(clientes);

        int databaseSizeBeforeUpdate = clientesRepository.findAll().size();

        // Update the clientes using partial update
        Clientes partialUpdatedClientes = new Clientes();
        partialUpdatedClientes.setId(clientes.getId());

        partialUpdatedClientes
            .nombre(UPDATED_NOMBRE)
            .apellidoPaterno(UPDATED_APELLIDO_PATERNO)
            .apellidoMaterno(UPDATED_APELLIDO_MATERNO)
            .fechaNacimiento(UPDATED_FECHA_NACIMIENTO)
            .identificacionOCR(UPDATED_IDENTIFICACION_OCR)
            .claveElector(UPDATED_CLAVE_ELECTOR)
            .telefono(UPDATED_TELEFONO)
            .celular(UPDATED_CELULAR)
            .correoElectronico(UPDATED_CORREO_ELECTRONICO);

        restClientesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClientes.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClientes))
            )
            .andExpect(status().isOk());

        // Validate the Clientes in the database
        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeUpdate);
        Clientes testClientes = clientesList.get(clientesList.size() - 1);
        assertThat(testClientes.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testClientes.getApellidoPaterno()).isEqualTo(UPDATED_APELLIDO_PATERNO);
        assertThat(testClientes.getApellidoMaterno()).isEqualTo(UPDATED_APELLIDO_MATERNO);
        assertThat(testClientes.getFechaNacimiento()).isEqualTo(UPDATED_FECHA_NACIMIENTO);
        assertThat(testClientes.getIdentificacionOCR()).isEqualTo(UPDATED_IDENTIFICACION_OCR);
        assertThat(testClientes.getClaveElector()).isEqualTo(UPDATED_CLAVE_ELECTOR);
        assertThat(testClientes.getTelefono()).isEqualTo(UPDATED_TELEFONO);
        assertThat(testClientes.getCelular()).isEqualTo(UPDATED_CELULAR);
        assertThat(testClientes.getCorreoElectronico()).isEqualTo(UPDATED_CORREO_ELECTRONICO);
    }

    @Test
    @Transactional
    void patchNonExistingClientes() throws Exception {
        int databaseSizeBeforeUpdate = clientesRepository.findAll().size();
        clientes.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, clientes.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(clientes))
            )
            .andExpect(status().isBadRequest());

        // Validate the Clientes in the database
        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchClientes() throws Exception {
        int databaseSizeBeforeUpdate = clientesRepository.findAll().size();
        clientes.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(clientes))
            )
            .andExpect(status().isBadRequest());

        // Validate the Clientes in the database
        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamClientes() throws Exception {
        int databaseSizeBeforeUpdate = clientesRepository.findAll().size();
        clientes.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientesMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(clientes)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Clientes in the database
        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteClientes() throws Exception {
        // Initialize the database
        clientesRepository.saveAndFlush(clientes);

        int databaseSizeBeforeDelete = clientesRepository.findAll().size();

        // Delete the clientes
        restClientesMockMvc
            .perform(delete(ENTITY_API_URL_ID, clientes.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Clientes> clientesList = clientesRepository.findAll();
        assertThat(clientesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
