package com.mibolsillo.controller;

import com.mibolsillo.domain.ReferenciaPersonales;
import com.mibolsillo.repository.ReferenciaPersonalesRepository;
import com.mibolsillo.controller.errors.BadRequestAlertException;
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
 * REST controller for managing {@link com.bancopoder.domain.ReferenciaPersonales}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ReferenciaPersonalesResource {

    private final Logger log = LoggerFactory.getLogger(ReferenciaPersonalesResource.class);

    private static final String ENTITY_NAME = "referenciaPersonales";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReferenciaPersonalesRepository referenciaPersonalesRepository;

    public ReferenciaPersonalesResource(ReferenciaPersonalesRepository referenciaPersonalesRepository) {
        this.referenciaPersonalesRepository = referenciaPersonalesRepository;
    }

    /**
     * {@code POST  /referencia-personales} : Create a new referenciaPersonales.
     *
     * @param referenciaPersonales the referenciaPersonales to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new referenciaPersonales, or with status {@code 400 (Bad Request)} if the referenciaPersonales has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/referencia-personales")
    public ResponseEntity<ReferenciaPersonales> createReferenciaPersonales(@RequestBody ReferenciaPersonales referenciaPersonales)
        throws URISyntaxException {
        log.debug("REST request to save ReferenciaPersonales : {}", referenciaPersonales);
        if (referenciaPersonales.getId() != null) {
            throw new BadRequestAlertException("A new referenciaPersonales cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReferenciaPersonales result = referenciaPersonalesRepository.save(referenciaPersonales);
        return ResponseEntity
            .created(new URI("/api/referencia-personales/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /referencia-personales/:id} : Updates an existing referenciaPersonales.
     *
     * @param id the id of the referenciaPersonales to save.
     * @param referenciaPersonales the referenciaPersonales to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated referenciaPersonales,
     * or with status {@code 400 (Bad Request)} if the referenciaPersonales is not valid,
     * or with status {@code 500 (Internal Server Error)} if the referenciaPersonales couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/referencia-personales/{id}")
    public ResponseEntity<ReferenciaPersonales> updateReferenciaPersonales(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ReferenciaPersonales referenciaPersonales
    ) throws URISyntaxException {
        log.debug("REST request to update ReferenciaPersonales : {}, {}", id, referenciaPersonales);
        if (referenciaPersonales.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, referenciaPersonales.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!referenciaPersonalesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ReferenciaPersonales result = referenciaPersonalesRepository.save(referenciaPersonales);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, referenciaPersonales.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /referencia-personales/:id} : Partial updates given fields of an existing referenciaPersonales, field will ignore if it is null
     *
     * @param id the id of the referenciaPersonales to save.
     * @param referenciaPersonales the referenciaPersonales to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated referenciaPersonales,
     * or with status {@code 400 (Bad Request)} if the referenciaPersonales is not valid,
     * or with status {@code 404 (Not Found)} if the referenciaPersonales is not found,
     * or with status {@code 500 (Internal Server Error)} if the referenciaPersonales couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/referencia-personales/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ReferenciaPersonales> partialUpdateReferenciaPersonales(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ReferenciaPersonales referenciaPersonales
    ) throws URISyntaxException {
        log.debug("REST request to partial update ReferenciaPersonales partially : {}, {}", id, referenciaPersonales);
        if (referenciaPersonales.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, referenciaPersonales.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!referenciaPersonalesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ReferenciaPersonales> result = referenciaPersonalesRepository
            .findById(referenciaPersonales.getId())
            .map(existingReferenciaPersonales -> {
                if (referenciaPersonales.getIdPersona() != null) {
                    existingReferenciaPersonales.setIdPersona(referenciaPersonales.getIdPersona());
                }
                if (referenciaPersonales.getNombre() != null) {
                    existingReferenciaPersonales.setNombre(referenciaPersonales.getNombre());
                }
                if (referenciaPersonales.getApellidoPaterno() != null) {
                    existingReferenciaPersonales.setApellidoPaterno(referenciaPersonales.getApellidoPaterno());
                }
                if (referenciaPersonales.getApellidoMaterno() != null) {
                    existingReferenciaPersonales.setApellidoMaterno(referenciaPersonales.getApellidoMaterno());
                }
                if (referenciaPersonales.getFechaNacimiento() != null) {
                    existingReferenciaPersonales.setFechaNacimiento(referenciaPersonales.getFechaNacimiento());
                }
                if (referenciaPersonales.getCelular() != null) {
                    existingReferenciaPersonales.setCelular(referenciaPersonales.getCelular());
                }
                if (referenciaPersonales.getTelefono() != null) {
                    existingReferenciaPersonales.setTelefono(referenciaPersonales.getTelefono());
                }
                if (referenciaPersonales.getComentarios() != null) {
                    existingReferenciaPersonales.setComentarios(referenciaPersonales.getComentarios());
                }

                return existingReferenciaPersonales;
            })
            .map(referenciaPersonalesRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, referenciaPersonales.getId().toString())
        );
    }

    /**
     * {@code GET  /referencia-personales} : get all the referenciaPersonales.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of referenciaPersonales in body.
     */
    @GetMapping("/referencia-personales")
    public List<ReferenciaPersonales> getAllReferenciaPersonales() {
        log.debug("REST request to get all ReferenciaPersonales");
        return referenciaPersonalesRepository.findAll();
    }

    /**
     * {@code GET  /referencia-personales/:id} : get the "id" referenciaPersonales.
     *
     * @param id the id of the referenciaPersonales to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the referenciaPersonales, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/referencia-personales/{id}")
    public ResponseEntity<ReferenciaPersonales> getReferenciaPersonales(@PathVariable Long id) {
        log.debug("REST request to get ReferenciaPersonales : {}", id);
        Optional<ReferenciaPersonales> referenciaPersonales = referenciaPersonalesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(referenciaPersonales);
    }

    /**
     * {@code DELETE  /referencia-personales/:id} : delete the "id" referenciaPersonales.
     *
     * @param id the id of the referenciaPersonales to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/referencia-personales/{id}")
    public ResponseEntity<Void> deleteReferenciaPersonales(@PathVariable Long id) {
        log.debug("REST request to delete ReferenciaPersonales : {}", id);
        referenciaPersonalesRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
