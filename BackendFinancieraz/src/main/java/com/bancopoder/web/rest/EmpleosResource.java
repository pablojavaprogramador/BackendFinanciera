package com.bancopoder.web.rest;

import com.bancopoder.domain.Empleos;
import com.bancopoder.repository.EmpleosRepository;
import com.bancopoder.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.bancopoder.domain.Empleos}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class EmpleosResource {

    private final Logger log = LoggerFactory.getLogger(EmpleosResource.class);

    private static final String ENTITY_NAME = "empleos";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmpleosRepository empleosRepository;

    public EmpleosResource(EmpleosRepository empleosRepository) {
        this.empleosRepository = empleosRepository;
    }

    /**
     * {@code POST  /empleos} : Create a new empleos.
     *
     * @param empleos the empleos to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new empleos, or with status {@code 400 (Bad Request)} if the empleos has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/empleos")
    public ResponseEntity<Empleos> createEmpleos(@RequestBody Empleos empleos) throws URISyntaxException {
        log.debug("REST request to save Empleos : {}", empleos);
        if (empleos.getId() != null) {
            throw new BadRequestAlertException("A new empleos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Empleos result = empleosRepository.save(empleos);
        return ResponseEntity
            .created(new URI("/api/empleos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /empleos/:id} : Updates an existing empleos.
     *
     * @param id the id of the empleos to save.
     * @param empleos the empleos to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated empleos,
     * or with status {@code 400 (Bad Request)} if the empleos is not valid,
     * or with status {@code 500 (Internal Server Error)} if the empleos couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/empleos/{id}")
    public ResponseEntity<Empleos> updateEmpleos(@PathVariable(value = "id", required = false) final Long id, @RequestBody Empleos empleos)
        throws URISyntaxException {
        log.debug("REST request to update Empleos : {}, {}", id, empleos);
        if (empleos.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, empleos.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!empleosRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Empleos result = empleosRepository.save(empleos);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, empleos.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /empleos/:id} : Partial updates given fields of an existing empleos, field will ignore if it is null
     *
     * @param id the id of the empleos to save.
     * @param empleos the empleos to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated empleos,
     * or with status {@code 400 (Bad Request)} if the empleos is not valid,
     * or with status {@code 404 (Not Found)} if the empleos is not found,
     * or with status {@code 500 (Internal Server Error)} if the empleos couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/empleos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Empleos> partialUpdateEmpleos(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Empleos empleos
    ) throws URISyntaxException {
        log.debug("REST request to partial update Empleos partially : {}, {}", id, empleos);
        if (empleos.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, empleos.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!empleosRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Empleos> result = empleosRepository
            .findById(empleos.getId())
            .map(existingEmpleos -> {
                if (empleos.getIngresosComprobables() != null) {
                    existingEmpleos.setIngresosComprobables(empleos.getIngresosComprobables());
                }
                if (empleos.getCargoPublico() != null) {
                    existingEmpleos.setCargoPublico(empleos.getCargoPublico());
                }
                if (empleos.getFamiliarCargoPublico() != null) {
                    existingEmpleos.setFamiliarCargoPublico(empleos.getFamiliarCargoPublico());
                }
                if (empleos.getCalle() != null) {
                    existingEmpleos.setCalle(empleos.getCalle());
                }
                if (empleos.getNumeroExterior() != null) {
                    existingEmpleos.setNumeroExterior(empleos.getNumeroExterior());
                }
                if (empleos.getCodigoPostal() != null) {
                    existingEmpleos.setCodigoPostal(empleos.getCodigoPostal());
                }

                return existingEmpleos;
            })
            .map(empleosRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, empleos.getId().toString())
        );
    }

    /**
     * {@code GET  /empleos} : get all the empleos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of empleos in body.
     */
    @GetMapping("/empleos")
    public List<Empleos> getAllEmpleos() {
        log.debug("REST request to get all Empleos");
        return empleosRepository.findAll();
    }

    /**
     * {@code GET  /empleos/:id} : get the "id" empleos.
     *
     * @param id the id of the empleos to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the empleos, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/empleos/{id}")
    public ResponseEntity<Empleos> getEmpleos(@PathVariable Long id) {
        log.debug("REST request to get Empleos : {}", id);
        Optional<Empleos> empleos = empleosRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(empleos);
    }

    /**
     * {@code DELETE  /empleos/:id} : delete the "id" empleos.
     *
     * @param id the id of the empleos to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/empleos/{id}")
    public ResponseEntity<Void> deleteEmpleos(@PathVariable Long id) {
        log.debug("REST request to delete Empleos : {}", id);
        empleosRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
